package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
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
		SysUserEntity currUser = ShiroUtils.getUserEntity();
//		boolean isSee = true;
//		if(currUser != null) {
//			List<Long> roles = currUser.getRoleIdList();
//			for(Long roleId:roles) {
//				if(roleId == 197) {
//					isSee = false;
//				}
//			}
//		}
		for(BPhotoInfoEntity bPhotoInfoEntity:lists) {
			if(!StringUtils.isEmpty(bPhotoInfoEntity.getPitureUrls())) {
				String [] arrs = bPhotoInfoEntity.getPitureUrls().split(",");
				String[] origin = bPhotoInfoEntity.getArraypitureUrl();
				for(int i=0;i<arrs.length;i++) {
					origin[i] = "/"+arrs[i];
				}
				bPhotoInfoEntity.setArraypitureUrl(origin);
			}
			if(bPhotoInfoEntity.getStatus().intValue() == 0) {
				bPhotoInfoEntity.setStatusDesc("已发布");
			}
			if(bPhotoInfoEntity.getStatus().intValue() == 1) {
				bPhotoInfoEntity.setStatusDesc("处理中");
			}
			if(bPhotoInfoEntity.getStatus().intValue() == 2) {
				bPhotoInfoEntity.setStatusDesc("已删除");
			}
            if(bPhotoInfoEntity.getStatus().intValue() == 3) {
                bPhotoInfoEntity.setStatusDesc("已处理");
            }

//			if(!isSee) {
//				bPhotoInfoEntity.setStatusDesc("未知");
//			}
		}
		return lists;
	}

	@Override
	public int saveBPhotoInfo(BPhotoInfoEntity bPhotoInfo) {
		return bPhotoInfoMapper.save(bPhotoInfo);
	}

	@Override
	public BPhotoInfoEntity getBPhotoInfoById(String id) {
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

	@Override
	public int passApply(String[] id) {
		int count = bPhotoInfoMapper.passApply(id);
		return count;
	}

    @Override
    public int getOperateCount(String operateId, String week, String month){
        int count = bPhotoInfoMapper.getOperateCount(operateId,week,month);
        return count;
    }

    @Override
    public List<BPhotoInfoEntity> listReplyPage(Page<BPhotoInfoEntity> page, Query search) {
        List<BPhotoInfoEntity> lists = bPhotoInfoMapper.listReplyPage(page, search);
        SysUserEntity currUser = ShiroUtils.getUserEntity();
        boolean isSee = true;
//        if(currUser != null) {
//            List<Long> roles = currUser.getRoleIdList();
//            for(Long roleId:roles) {
//                if(roleId == 197) {
//                    isSee = false;
//                }
//            }
//        }
        for(BPhotoInfoEntity bPhotoInfoEntity:lists) {
            if(!StringUtils.isEmpty(bPhotoInfoEntity.getPitureUrls())) {
                String [] arrs = bPhotoInfoEntity.getPitureUrls().split(",");
                String[] origin = bPhotoInfoEntity.getArraypitureUrl();
                for(int i=0;i<arrs.length;i++) {
                    origin[i] = "/"+arrs[i];
                }
                bPhotoInfoEntity.setArraypitureUrl(origin);
            }
            if(bPhotoInfoEntity.getStatus().intValue() == 0) {
                bPhotoInfoEntity.setStatusDesc("已发布");
            }
            if(bPhotoInfoEntity.getStatus().intValue() == 1) {
                bPhotoInfoEntity.setStatusDesc("处理中");
            }
            if(bPhotoInfoEntity.getStatus().intValue() == 2) {
                bPhotoInfoEntity.setStatusDesc("已删除");
            }
            if(bPhotoInfoEntity.getStatus().intValue() == 3) {
                bPhotoInfoEntity.setStatusDesc("已处理");
            }

            if(!isSee) {
                bPhotoInfoEntity.setStatusDesc("未知");
            }
        }
        return lists;
    }
}
