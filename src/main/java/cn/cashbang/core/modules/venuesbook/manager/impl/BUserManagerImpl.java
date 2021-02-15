package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BUserMapper;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.manager.BUserManager;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@Component("bUserManager")
public class BUserManagerImpl implements BUserManager {

	@Autowired
	private BUserMapper bUserMapper;
	

	@Override
	public List<BUserEntity> listBUser(Page<BUserEntity> page, Query search) {
		List<BUserEntity> lists = bUserMapper.listForPage(page, search);
		for(BUserEntity bu:lists) {
			if(bu.getStatus().intValue() == 1) {
				bu.setStatusStr("正常");
			}else if(bu.getStatus().intValue() == 2) {
				bu.setStatusStr("禁用");
			}
			if(bu.getSex().intValue() == 1) {
				bu.setSexStr("男");
			}else if(bu.getSex().intValue() == 0) {
				bu.setSexStr("女");
			}
		}
		return lists;
	}

	@Override
	public int saveBUser(BUserEntity bUser) {
		return bUserMapper.save(bUser);
	}

	@Override
	public BUserEntity getBUserById(String id) {
		BUserEntity bUser = bUserMapper.getObjectById(id);
		return bUser;
	}

	@Override
	public int updateBUser(BUserEntity bUser) {
		return bUserMapper.update(bUser);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bUserMapper.batchRemove(id);
		return count;
	}

	@Override
	public BUserEntity getUserByOpenId(String openId){

		BUserEntity bUser = bUserMapper.getUserByOpenId(openId);
		return bUser;
	}
}
