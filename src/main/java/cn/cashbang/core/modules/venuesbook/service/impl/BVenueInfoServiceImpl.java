package cn.cashbang.core.modules.venuesbook.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.utils.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.modules.venuesbook.entity.BUpdateVenueTime;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BDicManager;
import cn.cashbang.core.modules.venuesbook.manager.BVenueBookManager;
import cn.cashbang.core.modules.venuesbook.manager.BVenueInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BVenueInfoService;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@Service("bVenueInfoService")
public class BVenueInfoServiceImpl implements BVenueInfoService {

	@Autowired
	private BVenueInfoManager bVenueInfoManager;
	@Autowired
	private BVenueBookManager bVenueBookManager;
	@Autowired
	private BDicManager bDicManager;

	@Override
	public Page<BVenueInfoEntity> listBVenueInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BVenueInfoEntity> page = new Page<>(query);
		bVenueInfoManager.listBVenueInfo(page, query);
		return page;
	}

	@Override
	public Result saveBVenueInfo(MultipartFile file,BVenueInfoEntity role) {
		if(role == null) {
			return Result.error("对象为空");
		}
		//先检查场馆名称是已经存在
		BVenueInfoEntity old = bVenueInfoManager.getBVenueInfoByName(role.getVenueName());
		if(old != null) {
			return Result.error("该场馆已存在，请重新命名新场馆");
		}
		String uuid = CommonUtils.createUUID();
		role.setVenueId(uuid);
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
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		role.setIconUrl("/picture/"+fileName);
		int count = bVenueInfoManager.saveBVenueInfo(role);
		//默认禁用场馆的一个时段
		List<BDicEntity> dics = bDicManager.getDicsByCode("unAvaTime");
		for(BDicEntity b:dics) {
			BVenueBookEntity bbe = new BVenueBookEntity();
			bbe.setId(CommonUtils.createUUID());
			bbe.setBookStatus(1);
			bbe.setUserId("xtjy");
			bbe.setVenueId(role.getVenueId());
			bbe.setCreateTime(new Date());
			bbe.setUpdateTime(new Date());
			bbe.setBookTime(b.getName().split(" ")[0]);
			bbe.setActivityContent(b.getName().split(" ")[1]);
			bVenueBookManager.saveBVenueBook(bbe);
		}
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBVenueInfoById(String id) {
		BVenueInfoEntity bVenueInfo = bVenueInfoManager.getBVenueInfoById(id);
		return CommonUtils.msg(bVenueInfo);
	}

	@Override
	public Result updateBVenueInfo(MultipartFile file,BVenueInfoEntity bVenueInfo) {
		String uuid = bVenueInfo.getVenueId();
		String fileName = "";
		//先检查场馆名称是已经存在
		BVenueInfoEntity old = bVenueInfoManager.getBVenueInfoByName(bVenueInfo.getVenueName());
		if(old != null && !old.getVenueId().equals(bVenueInfo.getVenueId())) {
			return Result.error("该场馆已存在，请重新命名");
		}
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
                fileName = uuid+"-"+middle+"."+suffix;
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
            bVenueInfo.setIconUrl("/picture/"+fileName);
        }
		BVenueInfoEntity b = new BVenueInfoEntity();
		BeanUtils.copyProperties(bVenueInfo, b);
		b.setUpdateTime(new Date());
		int count = bVenueInfoManager.updateBVenueInfo(bVenueInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bVenueInfoManager.batchRemove(id);
		//删除场馆下的禁用时段
		for(String venueId:id) {
			bVenueBookManager.deleteByVenueId(venueId);
		}
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result updateUnableTime(BUpdateVenueTime bVenueInfo) {
		if(bVenueInfo != null && !StringUtils.isEmpty(bVenueInfo.getVenueId())) {
			String a = bVenueInfo.getDynamicTags();
			if(!StringUtils.isEmpty(a)) {
				//先删除原来的
				bVenueBookManager.deleteByVenueId(bVenueInfo.getVenueId());
				String aa[] = a.split(",");
				//新增新的禁用设置
				for(String b :aa) {
					b.replace("null", "");
					BVenueBookEntity bbe = new BVenueBookEntity();
					bbe.setId(CommonUtils.createUUID());
					bbe.setBookStatus(1);
					bbe.setUserId("xtjy");
					bbe.setVenueId(bVenueInfo.getVenueId());
					bbe.setCreateTime(new Date());
					bbe.setUpdateTime(new Date());
					bbe.setBookDate(b.split(" ")[0]);
					bbe.setBookTime(b.split(" ")[1]);
					bbe.setActivityContent(b.split(" ")[2]);
					bVenueBookManager.saveBVenueBook(bbe);
				}
			}else {
				//先删除原来的
				bVenueBookManager.deleteByVenueId(bVenueInfo.getVenueId());
			}
		}
		return CommonUtils.msg(1);
	}

	@Override
	public Result getDynamicTags(String venueId) {
		List<BVenueBookEntity> tags = bVenueBookManager.getVenueBookById(venueId);
		String [] dynamicTags = new String[tags.size()];
		for(int i=0;i<tags.size();i++) {
			dynamicTags[i] = tags.get(i).getBookDate()+" "+tags.get(i).getBookTime()+" "+tags.get(i).getActivityContent();
			dynamicTags[i] = dynamicTags[i].replace("null", "");
		}
		return Result.ok().put("dynamicTags", dynamicTags);
	}

	@Override
	public Result updateAllUnableTime(BUpdateVenueTime bVenueInfo) {
		if(bVenueInfo != null && !StringUtils.isEmpty(bVenueInfo.getVenueId())) {
			String a = bVenueInfo.getDynamicTags();
			
			if(!StringUtils.isEmpty(a)) {
				String aa[] = a.split(",");
				//先删除原来的
				bDicManager.deleteByTypeCode("unAvaTime");
				//新增新的禁用设置
				for(String b :aa) {
					b.replace("null", "");
					BDicEntity bbe = new BDicEntity();
					
					bbe.setId(bDicManager.getMaxId()+1);
					bbe.setCode(bDicManager.getMaxId()+1+"");
					bbe.setName(b);
					bbe.setStatus(1);
					bbe.setTypecode("unAvaTime");
					bbe.setTypename("所有场馆不可用时段");
					bbe.setCreateTime(new Date());
					bbe.setUpdateTime(new Date());
					bDicManager.saveBDic(bbe);
				}
			}else {
				//先删除原来的
				bDicManager.deleteByTypeCode("unAvaTime");
			}
		}
		return CommonUtils.msg(1);
	}

	@Override
	public Result getBookStatusList(BUpdateVenueTime bVenueInfo){

		// 根据场馆ID查询出该场馆的预约情况
		// List<BVenueBookEntity> tags = bVenueBookManager.getVenueBookById(bVenueInfo.getVenueId());

		// 计算出可以预约的七天日期
		ArrayList<String>  dateList = DateUtil.getDateList();


		JSONArray result  = new  JSONArray();
		for (int k =0;k<dateList.size();k ++){

			JSONObject object = new JSONObject();
			String date = dateList.get(k);
			object.put("date",date);
			JSONArray array = new  JSONArray();

			List<BDicEntity> bDics =bDicManager.getDicsByCode("avaTime");

			for (int i =0;i<bDics.size();i ++){

				JSONObject time = new JSONObject();
				time.put("time",bDics.get(i).getName());
				String venueId = bVenueInfo.getVenueId();
				// 根据场馆Id日期和时间段，判断该场馆的状态
				BVenueBookEntity entity = bVenueBookManager.getBookStatusById(
						venueId,date,bDics.get(i).getName());

				time.put("state",1);
				if(entity!=null){
					time.put("state",entity.getBookStatus());
				}
				array.add(time);
				object.put("list",array);
			}

			object.put("list",array);
			result.add(object);
		}

		return Result.ok().put("scheduleData", result);
	}
}
