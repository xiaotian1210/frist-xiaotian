package com.shareworx.ezfm.bjyijiequ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.bjyijiequ.BjyijiequUtils;

@Service(BjyijiequService.ID)
public class BjyijiequServiceImpl implements BjyijiequService{

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}
	
	@Override
	public void insertBjyijiequDb(String table_name) {
		
		/**
		 * 区域
		 */
		if ("tbb_area".equals(table_name)) {
			YJWYAreaModel[] areaModelArrs = BjyijiequUtils.getBjyijiequAreaDate();
			
			List<YJWYAreaModel> areaModel = service.save(YJWYAreaModel.META_ID, areaModelArrs);
			
			int size = areaModel.size();
			
			System.out.println("dms-DB-area:"+size+"条");
		}
		/**
		 * 项目
		 */
		if ("project_tab".equals(table_name)) {
			YJWYProjectModel[] projectModelArrs = BjyijiequUtils.getBjyijiequProjectDate();
			for (YJWYProjectModel yjwyProjectModel : projectModelArrs) {
				try {
					List<YJWYProjectModel> projectModel = service.save(YJWYProjectModel.META_ID, new YJWYProjectModel[]{yjwyProjectModel});
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("发生错误："+yjwyProjectModel);
				}
				
			}
			//List<YJWYProjectModel> projectModel = service.save(YJWYProjectModel.META_ID, projectModelArrs);
			
			//int size = projectModel.size();
			
			System.out.println("dms-DB-project:"+0+"条");
		}
		/**
		 * 资源
		 */
		if ("tbb_building_detail".equals(table_name)) {
			YJWYResourcesModel[] resourcesModelArrs = BjyijiequUtils.getBjyijiequResourcesDate();
			
			List<YJWYResourcesModel> resourcesModel = service.save(YJWYResourcesModel.META_ID, resourcesModelArrs);
			
			int size = resourcesModel.size();
			
			System.out.println("dms-DB-resources:"+size+"条");
		}
		
	}

}
