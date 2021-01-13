package cn.cashbang.core.modules.venuesbook.service.impl;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.HttpClientUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.manager.BUserManager;
import cn.cashbang.core.modules.venuesbook.service.BUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@Service("bUserService")
public class BUserServiceImpl implements BUserService {

	@Autowired
	private BUserManager bUserManager;

	@Override
	public Page<BUserEntity> listBUser(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BUserEntity> page = new Page<>(query);
		bUserManager.listBUser(page, query);
		return page;
	}

	@Override
	public Result saveBUser(BUserEntity role) {
		int count = bUserManager.saveBUser(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBUserById(String id) {
		BUserEntity bUser = bUserManager.getBUserById(id);
		return CommonUtils.msg(bUser);
	}

	@Override
	public Result updateBUser(BUserEntity bUser) {
		int count = bUserManager.updateBUser(bUser);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bUserManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result loginUser(String code){

		String appId="wx3a6796d91e05c5bf";
		String appSecret="dbf8c4107af70b407c9230705a4b126f";
		String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&appid="
				+ appId + "&secret=" + appSecret + "&js_code=" + code;
		//HttpClientUtils.HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(requestUrl);
		String res = HttpClientUtils.doGet1(requestUrl);

		JSONObject jsonObject = JSONObject.parseObject(res);
		Object resO = jsonObject.get("openid") == null ? jsonObject.get("openId") : jsonObject.get("openid");
		String openId = "";
		if(resO!=null){
			openId = openId.toString();
		}
		
		BUserEntity bUser = bUserManager.getBUserById("9b62829f1303468985cf0813b51f1ee7");

		bUser.setOpenId("test1234");
		bUserManager.updateBUser(bUser);

		if(StringUtils.isNotBlank(openId)){

			// 根据openId查询用户是否存在

			bUser.setOpenId(openId);
			bUserManager.updateBUser(bUser);
		}
		else {

			Result.error("登录失败");

		}

		return Result.ok().put("raws", bUser);

	}

}
