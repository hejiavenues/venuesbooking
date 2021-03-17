package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
public interface BPhotoInfoService {

	Page<BPhotoInfoEntity> listBPhotoInfo(Map<String, Object> params);
	
	Result saveBPhotoInfo(BPhotoInfoEntity bPhotoInfo);
	
	Result getBPhotoInfoById(String id);
	
	Result updateBPhotoInfo(BPhotoInfoEntity bPhotoInfo);
	
	Result batchRemove(String[] id);

	Result saveImage(MultipartFile imgFile,String type);

	Result passApply(String[] id);

    int getOperateCount(String operateId, String week, String month);

    Page<BPhotoInfoEntity> listReplyPage(Map<String, Object> params);

    Result updateStaus(BPhotoInfoEntity bPhotoInfo, SysUserEntity user);
}
