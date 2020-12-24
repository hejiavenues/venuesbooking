package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoDto;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
public interface BBannerInfoService {

	Page<BBannerInfoEntity> listBBannerInfo(Map<String, Object> params);
	
	
	Result getBBannerInfoById(String id);
	
	Result updateBBannerInfo(MultipartFile imgFile,BBannerInfoDto bBannerInfo);
	
	Result batchRemove(String[] id);

	Result saveBBannerInfo(MultipartFile imgFile, BBannerInfoEntity bBannerInfo);
	
}
