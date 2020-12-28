package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoDto;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BBannerInfoService;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
@RestController
@RequestMapping("/venuesbook/banner")
public class BBannerInfoController extends AbstractController {
	
	@Autowired
	private BBannerInfoService bBannerInfoService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BBannerInfoEntity> list(@RequestBody Map<String, Object> params) {
		return bBannerInfoService.listBBannerInfo(params);
	}
		
	/**
	 * 新增
	 * @param bBannerInfo
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(MultipartFile imgFile, BBannerInfoEntity bBannerInfo) {
		logger.info("新增banner配置开始，bBannerInfo：{}",bBannerInfo.toString());
		Result resultEntity = new Result();
		if(imgFile == null){
			logger.error("新增banner配置信息，imgFile为空");
			resultEntity = Result.error(100, "banner配置信息图片为空");
			return resultEntity;
		}
		return bBannerInfoService.saveBBannerInfo(imgFile,bBannerInfo);
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
		return bBannerInfoService.getBBannerInfoById(id);
	}
	
	/**
	 * 修改
	 * @param bBannerInfo
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody MultipartFile imgFile, BBannerInfoDto bBannerInfo) {
		return bBannerInfoService.updateBBannerInfo(imgFile,bBannerInfo);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bBannerInfoService.batchRemove(id);
	}
	
}
