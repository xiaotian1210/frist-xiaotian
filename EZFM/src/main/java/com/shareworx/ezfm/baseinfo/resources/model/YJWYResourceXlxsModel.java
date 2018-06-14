package com.shareworx.ezfm.baseinfo.resources.model;

import org.springframework.web.multipart.MultipartFile;

public class YJWYResourceXlxsModel {
	// "resourcesType6_id","project_id","reourcesType1_name","resourcesType2_name","resourcesType3_name","resourcesType4_name","resourcesType5_name","resourcesType6_name"
	//"resourcesType1","resourcesType2","resourcesType3","resourcesType4","resourcesType5","resourcesType6"
	String pk_resources;
	String project_name;
	String resourcesType1;
	String resourcesType2;
	String resourcesType3;
	String resourcesType4;
	String resourcesType5;
	String resourcesType6;
	String import_type;
	String project_code;
	MultipartFile excle_file;
	
	
	
	
	
	public MultipartFile getExcle_file() {
		return excle_file;
	}
	public void setExcle_file(MultipartFile excle_file) {
		this.excle_file = excle_file;
	}
	public String getImport_type() {
		return import_type;
	}
	public void setImport_type(String import_type) {
		this.import_type = import_type;
	}
	public String getProject_code() {
		return project_code;
	}
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	public String getPk_resources() {
		return pk_resources;
	}
	public void setPk_resources(String pk_resources) {
		this.pk_resources = pk_resources;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getResourcesType1() {
		return resourcesType1;
	}
	public void setResourcesType1(String resourcesType1) {
		this.resourcesType1 = resourcesType1;
	}
	public String getResourcesType2() {
		return resourcesType2;
	}
	public void setResourcesType2(String resourcesType2) {
		this.resourcesType2 = resourcesType2;
	}
	public String getResourcesType3() {
		return resourcesType3;
	}
	public void setResourcesType3(String resourcesType3) {
		this.resourcesType3 = resourcesType3;
	}
	public String getResourcesType4() {
		return resourcesType4;
	}
	public void setResourcesType4(String resourcesType4) {
		this.resourcesType4 = resourcesType4;
	}
	public String getResourcesType5() {
		return resourcesType5;
	}
	public void setResourcesType5(String resourcesType5) {
		this.resourcesType5 = resourcesType5;
	}
	public String getResourcesType6() {
		return resourcesType6;
	}
	public void setResourcesType6(String resourcesType6) {
		this.resourcesType6 = resourcesType6;
	}
	
	

	

}
