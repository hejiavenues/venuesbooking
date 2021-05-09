package cn.cashbang.core.modules.venuesbook.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.cashbang.core.common.utils.HttpPostUtil;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.common.utils.WebUtils;
import cn.cashbang.core.modules.venuesbook.entity.*;
import cn.cashbang.core.modules.venuesbook.manager.*;
import com.alibaba.fastjson.JSONObject;
import groovy.transform.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@Service("bActivitiesService")
public class BActivitiesServiceImpl implements BActivitiesService {

	@Autowired
	private BActivitiesManager bActivitiesManager;

    @Autowired
    private BVenueBookManager bVenueBookManager;

    @Autowired
    private BAccessTokenManager bAccessTokenManager;

    @Autowired
    private BActivityEntryManager bActivityEntryManager;

    @Autowired
    private BVenueInfoManager bVenueInfoManager;


    @Override
	public Page<BActivitiesEntity> listBActivities(Map<String, Object> params) {
		
		Query query = new Query(params);
		Page<BActivitiesEntity> page = new Page<>(query);
		bActivitiesManager.listBActivities(page, query);
		return page;
	}

	@Synchronized
	@Override
	public Result saveBActivities(BActivitiesEntity role,String bookDate,String bookTime) {

        String clientId="6d41739156ab4a31b517a73990913db6";
        String accessToken="23213669f41fc9a9a0fbe2a9850563d0";
        // 获得日历对象
        Calendar c = Calendar. getInstance ();
        // 获得当前时间的毫秒值
        long todayTime = c.getTimeInMillis();


        BVenueBookEntity entity = bVenueBookManager.getBookStatusById(
                role.getVenueId(),bookDate,bookTime);

        if(entity!=null){
            return Result.error("该时段已经被人抢先预约，请预约其他空闲时段。");
        }

        int count = 0;
        BAccessTokenEntity token = bAccessTokenManager.getBAccessTokenById(1L);
        String content = role.getActivityContent()+role.getActivityIdName();
        String code = WebUtils.msgCheck(token.getAccessToken(),content);
        System.out.println("content"+content);
        if("40001".equals(code)||"42001".equals(code)){
            String tokenString = WebUtils.getAccessToken();
            if(cn.cashbang.core.common.utils.StringUtils.isNotBlank(tokenString)){

                BAccessTokenEntity bAccessToken = new BAccessTokenEntity();
                bAccessToken.setId(1);
                bAccessToken.setAccessToken(tokenString);
                bAccessTokenManager.updateBAccessToken(bAccessToken);
            }
            code = WebUtils.msgCheck(tokenString,content);
        }
        if("0".equals(code)){


            try {

                String time =bookTime;
                String timearray[] = time.split("-");
                String date = bookDate+" " + timearray[0]+":00 000";
                System.out.println("开始时间："+date);
                String date2 = bookDate+" " + timearray[1]+":00 000";
                System.out.println("结束时间："+date2);
               // try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(date));
                    String startDate = String.valueOf(calendar.getTimeInMillis()-900000L);
                    System.out.println("日期1对应毫秒：" + startDate);
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(date2));
                    String endDate = String.valueOf(calendar.getTimeInMillis());
                    System.out.println("日期2对应毫秒：" + endDate);

//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                int r2 = bActivitiesManager.saveBActivities(role);
                BVenueInfoEntity venueInfo = bVenueInfoManager.getBVenueInfoById(role.getVenueId());

                if(venueInfo == null || cn.cashbang.core.common.utils.StringUtils.isEmpty(venueInfo.getLockId()))  {
                    return Result.error().put("msg","该房间还没有绑定锁!");
                }

                //            // 生成密码
                String requestUrlPwd="https://api.sciener.com/v3/keyboardPwd/get";
                Map<String,String> params2 = new HashMap<>();
                params2.put("clientId",clientId);
                params2.put("accessToken",accessToken);
                params2.put("lockId",venueInfo.getLockId());
                params2.put("keyboardPwdVersion","4");
                params2.put("keyboardPwdType","3");
                params2.put("startDate",startDate);
                params2.put("endDate",endDate);
                params2.put("date",String.valueOf(todayTime));
                String resPwd = HttpPostUtil.sendPostRequest(requestUrlPwd,params2);
                System.out.println("设置密码接口返回结果："+resPwd);

                JSONObject resultPwd =  JSONObject.parseObject(resPwd);
                String keyboardPwdId = resultPwd.getString("keyboardPwdId");
                String keyboardPwd = resultPwd.getString("keyboardPwd");
                
                // 生成一条预约记录
                BVenueBookEntity bVenueBook = new BVenueBookEntity();
                bVenueBook.setBookStatus(2); // 已预约
                bVenueBook.setBookTime(bookTime);
                bVenueBook.setBookDate(bookDate);
                bVenueBook.setUserId(role.getUid());
                bVenueBook.setVenueId(role.getVenueId());
                bVenueBook.setActivityId(role.getActivityId());
                bVenueBook.setActivityContent(role.getActivityContent());
                String uuid = CommonUtils.createUUID();
                bVenueBook.setId(uuid);
                bVenueBook.setCreateTime(new Date());
                bVenueBook.setKeyboardPwd(keyboardPwd);
                bVenueBook.setKeyboardPwdId(keyboardPwdId);
                int r1= bVenueBookManager.saveBVenueBook(bVenueBook);
                count = r1+r2;

            if(count==2){
                count = 1;
            }

            } catch (Exception e) {
                e.printStackTrace();
                return Result.error().put("msg","房间密码获取失败!");
            }
        }
        else {
            return Result.error("上传的信息中含有敏感内容，请修改后再上传。");
        }

		return CommonUtils.msg(count);
	}

    @Override
    public Result saveBActivitiesHoutai(MultipartFile file, BActivitiesEntity role, String bookDate, String bookTime) {

        int count = 0;
        String uuid = CommonUtils.createUUID();
        String fileName = "";
        if(file != null){
            //取得当前上传文件的文件名称
            String myFileName = file.getOriginalFilename();
            System.out.println("----"+file.getName());
            String suffix = myFileName.substring(myFileName.lastIndexOf(".") + 1);
            String middle = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            String uploadUrl = SpringContextUtils.getApplicationProperties().getUploadInfo().get("imageurl");
            if(myFileName.trim() !=""){
                //重命名上传后的文件名
                fileName = uuid.substring(0, 8)+"-"+middle+"."+suffix;
                File dirPath = new File(uploadUrl);
                if(!dirPath.exists()){
                    dirPath.mkdirs();
                }

                //定义上传路径
                String path = dirPath + File.separator + fileName;
                File localFile = new File(path);
                try {
                    file.transferTo(localFile);
                } catch (IllegalStateException|IOException e) {
                    e.printStackTrace();
                    return Result.error("导入图片失败");
                }
            }
        }


            role.setActivityIconUrl("/picture/"+fileName);
            int r2 = bActivitiesManager.saveBActivities(role);

        try {

            String clientId="6d41739156ab4a31b517a73990913db6";
            String accessToken="23213669f41fc9a9a0fbe2a9850563d0";
            // 获得日历对象
            Calendar c = Calendar. getInstance ();
            // 获得当前时间的毫秒值
            long todayTime = c.getTimeInMillis();

            String time =bookTime;
            String timearray[] = time.split("-");
            String date = bookDate+" " + timearray[0]+":00 000";
            System.out.println("开始时间："+date);
            String date2 = bookDate+" " + timearray[1]+":00 000";
            System.out.println("结束时间："+date2);
            // try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(date));
            String startDate = String.valueOf(calendar.getTimeInMillis()-900000L);
            System.out.println("日期1对应毫秒：" + startDate);
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(date2));
            String endDate = String.valueOf(calendar.getTimeInMillis());
            System.out.println("日期2对应毫秒：" + endDate);

            BVenueInfoEntity venueInfo = bVenueInfoManager.getBVenueInfoById(role.getVenueId());

            if(venueInfo == null || cn.cashbang.core.common.utils.StringUtils.isEmpty(venueInfo.getLockId()))  {
                return Result.error().put("msg","该房间还没有绑定锁!");
            }

            //            // 生成密码
            String requestUrlPwd="https://api.sciener.com/v3/keyboardPwd/get";
            Map<String,String> params2 = new HashMap<>();
            params2.put("clientId",clientId);
            params2.put("accessToken",accessToken);
            params2.put("lockId",venueInfo.getLockId());
            params2.put("keyboardPwdVersion","4");
            params2.put("keyboardPwdType","3");
            params2.put("startDate",startDate);
            params2.put("endDate",endDate);
            params2.put("date",String.valueOf(todayTime));
            String resPwd = HttpPostUtil.sendPostRequest(requestUrlPwd,params2);
            System.out.println("设置密码接口返回结果："+resPwd);

            JSONObject resultPwd =  JSONObject.parseObject(resPwd);
            String keyboardPwdId = resultPwd.getString("keyboardPwdId");
            String keyboardPwd = resultPwd.getString("keyboardPwd");

            // 生成一条预约记录
            BVenueBookEntity bVenueBook = new BVenueBookEntity();
            bVenueBook.setBookStatus(2); // 已预约
            bVenueBook.setBookTime(bookTime);
            bVenueBook.setBookDate(bookDate);
            bVenueBook.setUserId(role.getUid());
            bVenueBook.setVenueId(role.getVenueId());
            bVenueBook.setActivityId(role.getActivityId());
            bVenueBook.setActivityContent(role.getActivityContent());
            String uuid2 = CommonUtils.createUUID();
            bVenueBook.setId(uuid2);
            bVenueBook.setCreateTime(new Date());
            bVenueBook.setKeyboardPwd(keyboardPwd);
            bVenueBook.setKeyboardPwdId(keyboardPwdId);
            int r1= bVenueBookManager.saveBVenueBook(bVenueBook);
            count = r1+r2;

            if(count==2){
                count = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().put("msg","房间密码获取失败!");
        }

        return CommonUtils.msg(count);
    }

	@Override
	public Result getBActivitiesById(String id) {
		BActivitiesEntity bActivities = bActivitiesManager.getBActivitiesById(id);
		return CommonUtils.msg(bActivities);
	}

	@Override
	public Result updateBActivities(BActivitiesEntity bActivities) {
		int count = bActivitiesManager.updateBActivities(bActivities);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bActivitiesManager.batchRemove(id);

		// 删除预约信息
        bActivityEntryManager.deleteByActivityId(id[0]);
        // 删除报名信息
        bVenueBookManager.deleteByActivityId(id[0]);
		return CommonUtils.msg(id, count);
	}

	@Override
	public  Result listActByUserId(String uid) {
		List<BActivitiesEntity>  list = bActivitiesManager.listActByUserId(uid);
		return  Result.ok().put("raws", list);
	}
}
