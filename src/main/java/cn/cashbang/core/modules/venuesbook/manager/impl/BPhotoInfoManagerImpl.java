package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BPhotoInfoMapper;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BPhotoInfoManager;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@Component("bPhotoInfoManager")
public class BPhotoInfoManagerImpl implements BPhotoInfoManager {

	@Autowired
	private BPhotoInfoMapper bPhotoInfoMapper;
	

	@Override
	public List<BPhotoInfoEntity> listBPhotoInfo(Page<BPhotoInfoEntity> page, Query search) {
		List<BPhotoInfoEntity> lists = bPhotoInfoMapper.listForPage(page, search);
		for(BPhotoInfoEntity bPhotoInfoEntity:lists) {
			if(!StringUtils.isEmpty(bPhotoInfoEntity.getPitureUrls())) {
				String [] arrs = bPhotoInfoEntity.getPitureUrls().split(",");
				String[] origin = bPhotoInfoEntity.getArraypitureUrl();
				for(int i=0;i<arrs.length;i++) {
					origin[i] = "/picture/"+arrs[i];
				}
				bPhotoInfoEntity.setArraypitureUrl(origin);
			}
			if(bPhotoInfoEntity.getStatus().intValue() == 1) {
				bPhotoInfoEntity.setStatusDesc("正常");
			}
			if(bPhotoInfoEntity.getStatus().intValue() == 2) {
				bPhotoInfoEntity.setStatusDesc("已删除");
			}
		}
		return lists;
	}

	@Override
	public int saveBPhotoInfo(BPhotoInfoEntity bPhotoInfo) {
		return bPhotoInfoMapper.save(bPhotoInfo);
	}

	@Override
	public BPhotoInfoEntity getBPhotoInfoById(Long id) {
		BPhotoInfoEntity bPhotoInfo = bPhotoInfoMapper.getObjectById(id);
		return bPhotoInfo;
	}

	@Override
	public int updateBPhotoInfo(BPhotoInfoEntity bPhotoInfo) {
		return bPhotoInfoMapper.update(bPhotoInfo);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bPhotoInfoMapper.batchRemove(id);
		return count;
	}
	
}
