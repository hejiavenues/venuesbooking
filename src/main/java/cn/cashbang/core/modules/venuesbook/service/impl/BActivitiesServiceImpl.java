package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.utils.WebUtils;
import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.manager.BAccessTokenManager;
import cn.cashbang.core.modules.venuesbook.manager.BVenueBookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BActivitiesManager;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;

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

	@Override
	public Page<BActivitiesEntity> listBActivities(Map<String, Object> params) {
		
		Query query = new Query(params);
		Page<BActivitiesEntity> page = new Page<>(query);
		bActivitiesManager.listBActivities(page, query);
		return page;
	}

	@Override
	public Result saveBActivities(BActivitiesEntity role,String bookDate,String bookTime) {

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

            int r2 = bActivitiesManager.saveBActivities(role);

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
            int r1= bVenueBookManager.saveBVenueBook(bVenueBook);
            count = r1+r2;

            if(count==2){
                count = 1;
            }
        }
        else {
            return Result.error("上传的信息中含有敏感内容，请修改后再上传。");
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
		return CommonUtils.msg(id, count);
	}

	@Override
	public  Result listActByUserId(String uid) {
		List<BActivitiesEntity>  list = bActivitiesManager.listActByUserId(uid);
		return  Result.ok().put("raws", list);
	}
}
