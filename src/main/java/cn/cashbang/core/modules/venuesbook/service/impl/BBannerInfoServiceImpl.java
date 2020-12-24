package cn.cashbang.core.modules.venuesbook.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoDto;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BBannerInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BBannerInfoService;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
@Service("bBannerInfoService")
public class BBannerInfoServiceImpl implements BBannerInfoService {

	@Autowired
	private BBannerInfoManager bBannerInfoManager;

	@Override
	public Page<BBannerInfoEntity> listBBannerInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BBannerInfoEntity> page = new Page<>(query);
		bBannerInfoManager.listBBannerInfo(page, query);
		return page;
	}

	@Override
	public Result saveBBannerInfo(MultipartFile file,BBannerInfoEntity role) {
		if(role == null) {
			return Result.error("对象为空");
		}
		String uuid = CommonUtils.createUUID();
		role.setBid(uuid);
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
        }
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		role.setBannerImgUrl("/picture/"+fileName);
		int count = bBannerInfoManager.saveBBannerInfo(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBBannerInfoById(String id) {
		BBannerInfoEntity bBannerInfo = bBannerInfoManager.getBBannerInfoById(id);
		return CommonUtils.msg(bBannerInfo);
	}

	@Override
	public Result updateBBannerInfo(MultipartFile file,BBannerInfoDto bBannerInfo) {
		String uuid = bBannerInfo.getBid();
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
            bBannerInfo.setBannerImgUrl("/picture/"+fileName);
        }
		BBannerInfoEntity b = new BBannerInfoEntity();
		BeanUtils.copyProperties(bBannerInfo, b);
		b.setUpdateTime(new Date());
		int count = bBannerInfoManager.updateBBannerInfo(b);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bBannerInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
