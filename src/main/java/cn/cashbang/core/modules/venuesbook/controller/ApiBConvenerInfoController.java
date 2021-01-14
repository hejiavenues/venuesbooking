package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BConvenerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import java.util.Map;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
@RestController
@RequestMapping("/api/convenerinfo")
public class ApiBConvenerInfoController extends AbstractController {
	
	@Autowired
	private BConvenerInfoService bConvenerInfoService;
		
	/**
	 * 申请成为召集人
	 * @param idcardFrontUrl
	 * @return
	 */
	@RequestMapping("/applyConvener")
	public Result applyConvener(String userId,String idcardFrontUrl,String idcardBackUrl,Integer activityId) {

		BConvenerInfoEntity  bConvenerInfo=new BConvenerInfoEntity();

		bConvenerInfo.setIdcardBackUrl(idcardBackUrl);
		bConvenerInfo.setIdcardFrontUrl(idcardFrontUrl);
		bConvenerInfo.setActivityId(activityId);
		//String uuid = CommonUtils.createUUID();
		bConvenerInfo.setStatus(0); // 审核中
		bConvenerInfo.setUid(userId);

		return bConvenerInfoService.saveBConvenerInfo(bConvenerInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody String id) {
		if(!StringUtils.isEmpty(id)) {
			id = id.replace("\"", "");
		}
		return bConvenerInfoService.getBConvenerInfoById(id);
	}
	
}
