package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.common.utils.WebUtils;
import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;
import cn.cashbang.core.modules.venuesbook.manager.BAccessTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.manager.BTeamManager;
import cn.cashbang.core.modules.venuesbook.service.BTeamService;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@Service("bTeamService")
public class BTeamServiceImpl implements BTeamService {

	@Autowired
	private BTeamManager bTeamManager;

	@Autowired
	private BAccessTokenManager bAccessTokenManager;

	@Override
	public Page<BTeamEntity> listBTeam(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BTeamEntity> page = new Page<>(query);
		bTeamManager.listBTeam(page, query);
		return page;
	}

	@Override
	public Result saveBTeam(BTeamEntity role) {

        int count = 0;

        BAccessTokenEntity token = bAccessTokenManager.getBAccessTokenById(1L);
        String content = role.getActivityContent()+role.getTname()+role.getEnterCondition();
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
            int teamNo = bTeamManager.countTeamByCreateUserId(role.getUid());

            if(teamNo>1){
                return Result.error("您本月创建的团队已经超过两个，不能再创建团队了。");
            }

            count = bTeamManager.saveBTeam(role);
        }
        else {
            return Result.error("上传的信息中含有敏感内容，请修改后再上传。");
        }

		return CommonUtils.msg(count);
	}

	@Override
	public Result getBTeamById(String id) {
		BTeamEntity bTeam = bTeamManager.getBTeamById(id);
		return CommonUtils.msg(bTeam);
	}

	@Override
	public Result updateBTeam(BTeamEntity bTeam) {
		int count = bTeamManager.updateBTeam(bTeam);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bTeamManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result listTeamByUserId(String uid){
		List<BTeamEntity> list = bTeamManager.listTeamByUserId(uid);
		return Result.ok().put("raws", list);
	}

	@Override
	public Result listTeamByCreateUserId(String uid){
		List<BTeamEntity> list = bTeamManager.listTeamByCreateUserId(uid);
		return Result.ok().put("raws", list);
	}
}
