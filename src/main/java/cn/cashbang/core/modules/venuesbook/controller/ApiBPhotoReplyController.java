package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;
import cn.cashbang.core.modules.venuesbook.service.BPhotoReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
@RestController
@RequestMapping("/api/photoreply")
public class ApiBPhotoReplyController extends AbstractController {
	
	@Autowired
	private BPhotoReplyService bPhotoReplyService;
	
	/**
	 * 列表
	 * @param photoId
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(String photoId) {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("pageNumber",1);
        params.put("pageSize",100);
        params.put("reply",photoId); 
        params.put("sortOrde","asc");


        Page<BPhotoReplyEntity> list = bPhotoReplyService.listBPhotoReply(params);
        ;
        if(list.getTotal()>0){
            result.put("code",0);
            result.put("rows",list.getRows());
            result.put("msg","查询成功！");
        }
        else{
            result.put("code",-1);
            result.put("rows",null);
            result.put("msg","还没有人评论过，快来评论吧！");
        }

		return result;
	}
		
	/**
	 * 新增
	 * @param photoId
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/saveReply")
	public Result save(String uid,String content,String photoId) {

        BPhotoReplyEntity bPhotoReply = new BPhotoReplyEntity();
        bPhotoReply.setUid(uid);
        bPhotoReply.setPhotoId(photoId);

        bPhotoReply.setContent(content);
        String uuid = CommonUtils.createUUID();
        bPhotoReply.setReply(uuid);
        bPhotoReply.setCreateTime(new Date());
		return bPhotoReplyService.saveBPhotoReply(bPhotoReply);
	}
}
