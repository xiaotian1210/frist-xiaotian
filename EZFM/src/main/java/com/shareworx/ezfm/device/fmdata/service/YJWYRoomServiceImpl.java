package com.shareworx.ezfm.device.fmdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.device.fmdata.dao.YJWYRoomDao;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;

/**
 * 机房列表业务查询操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYRoomService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYRoomServiceImpl implements YJWYRoomService {

	@Autowired
	@Qualifier(YJWYRoomDao.ID)
	private YJWYRoomDao roomDao;

	public void setRoomDao(YJWYRoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProjectService;

	public void setSiteProjectService(YJWYSiteProjectService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Override
	public List<YJWYRoomModel> queryRoomModels(String pk_project) {
		List<YJWYRoomModel> list = new ArrayList<>();
		if (!DeviceUtil.stringIsEmpty(pk_project)) {
			// 获取fm项目集合
//			String[] siteIds = siteProjectService.queryIds(new String[] { pk_project });
			String[] siteIds = new String[] { pk_project };
			if (DeviceUtil.arrayIsNotEmpty(siteIds)) {
				Query query = Query.from(YJWYRoomModel.META_ID);
				query.where(new Condition("site_id", QueryContents.TYPE_IN, siteIds));
				list = roomDao.queryListByCondition(query);
			}
		}
		return list;
	}

}
