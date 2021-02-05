package cn.cashbang.core.modules.venuesbook.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.common.utils.WebUtils;
import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.manager.BAccessTokenManager;
import cn.cashbang.core.modules.venuesbook.manager.BUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BPhotoInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BPhotoInfoService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@Service("bPhotoInfoService")
public class BPhotoInfoServiceImpl implements BPhotoInfoService {

	@Autowired
	private BPhotoInfoManager bPhotoInfoManager;

	@Autowired
	private BUserManager bUserManager;

    @Autowired
    private BAccessTokenManager bAccessTokenManager;

	@Override
	public Page<BPhotoInfoEntity> listBPhotoInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BPhotoInfoEntity> page = new Page<>(query);
		bPhotoInfoManager.listBPhotoInfo(page, query);
		return page;
	}

	@Override
	public Result saveBPhotoInfo(BPhotoInfoEntity role) {

        int count = 0;
        BAccessTokenEntity token = bAccessTokenManager.getBAccessTokenById(1L);
        String content = role.getContent();
        String code = WebUtils.msgCheck(token.getAccessToken(),content);
        System.out.println("content"+content);
        if("40001".equals(code)||"42001".equals(code)){
            String tokenString = WebUtils.getAccessToken();
            if(StringUtils.isNotBlank(tokenString)){

                BAccessTokenEntity bAccessToken = new BAccessTokenEntity();
                bAccessToken.setId(1);
                bAccessToken.setAccessToken(tokenString);
                bAccessTokenManager.updateBAccessToken(bAccessToken);
            }
            code = WebUtils.msgCheck(tokenString,content);
        }
        if("0".equals(code)){

            BUserEntity bUser = bUserManager.getBUserById(role.getUid());
            role.setUname(bUser.getUname());
            count = bPhotoInfoManager.saveBPhotoInfo(role);
        }
        else {
            return Result.error("上传的信息中含有敏感内容，请修改后再上传。");
        }

		return CommonUtils.msg(count);
	}

	@Override
	public Result getBPhotoInfoById(Long id) {
		BPhotoInfoEntity bPhotoInfo = bPhotoInfoManager.getBPhotoInfoById(id);
		return CommonUtils.msg(bPhotoInfo);
	}

	@Override
	public Result updateBPhotoInfo(BPhotoInfoEntity bPhotoInfo) {
		int count = bPhotoInfoManager.updateBPhotoInfo(bPhotoInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bPhotoInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result saveImage(MultipartFile file, String type){
        
        BAccessTokenEntity token = bAccessTokenManager.getBAccessTokenById(1L);
        String code = WebUtils.imgCheck(token.getAccessToken(),file);

        if("40001".equals(code)||"42001".equals(code)){
            String tokenString = WebUtils.getAccessToken();
            if(cn.cashbang.core.common.utils.StringUtils.isNotBlank(tokenString)){

                BAccessTokenEntity bAccessToken = new BAccessTokenEntity();
                bAccessToken.setId(1);
                bAccessToken.setAccessToken(tokenString);
                bAccessTokenManager.updateBAccessToken(bAccessToken);
            }
            code = WebUtils.imgCheck(tokenString,file);
        }

        if("0".equals(code)){

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
                        return Result.ok().put("raws", fileName);
                    } catch (IllegalStateException|IOException e) {
                        e.printStackTrace();
                        return Result.error("导入图片失败");
                    }
                }
                else  {
                    return Result.error("图片内容为空");
                }
            }
            else {
                return Result.error("图片内容为空");
            }
        }
        else {
            return Result.error("上传的信息中含有敏感内容，请修改后再上传。");
        }
	}

	@Override
	public Result passApply(String[] id) {
		int count = bPhotoInfoManager.passApply(id);
		return CommonUtils.msg(id, count);
	}
}
