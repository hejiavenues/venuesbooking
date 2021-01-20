package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.ArrayList;
import java.util.List;

import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.modules.venuesbook.dao.BDicMapper;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BVenueInfoMapper;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BVenueInfoManager;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@Component("bVenueInfoManager")
public class BVenueInfoManagerImpl implements BVenueInfoManager {

	@Autowired
	private BVenueInfoMapper bVenueInfoMapper;

	@Autowired
	private BDicMapper bDicMapper;

	@Override
	public List<BVenueInfoEntity> listBVenueInfo(Page<BVenueInfoEntity> page, Query search) {

		List<BVenueInfoEntity> list =    bVenueInfoMapper.listForPage(page, search);
		List<BVenueInfoEntity> listnew = new ArrayList<>() ;
		for (BVenueInfoEntity venue:  list) {
			String ats = venue.getSupportActiveType();
			String atsdes = "";
			String[] atarry = ats.split(",");
			for (String a:atarry){
				BDicEntity dic = bDicMapper.getBActivityDicByCode(a);
				String name = dic.getName();

				if(StringUtils.isNotBlank(atsdes)) {
					atsdes = atsdes +","+ name;
				}
				else {
					atsdes = name;
				}
			}

			venue.setSupportActiveTypeDes(atsdes);
			listnew.add(venue);
		}
		
		return listnew;
	}

	@Override
	public int saveBVenueInfo(BVenueInfoEntity bVenueInfo) {
		return bVenueInfoMapper.save(bVenueInfo);
	}

	@Override
	public BVenueInfoEntity getBVenueInfoById(String id) {
		BVenueInfoEntity bVenueInfo = bVenueInfoMapper.getObjectById(id);
		return bVenueInfo;
	}

	@Override
	public int updateBVenueInfo(BVenueInfoEntity bVenueInfo) {
		return bVenueInfoMapper.update(bVenueInfo);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bVenueInfoMapper.batchRemove(id);
		return count;
	}

	@Override
	public BVenueInfoEntity getBVenueInfoByName(String venueName) {
		return bVenueInfoMapper.getBVenueInfoByName(venueName);
	}
}
