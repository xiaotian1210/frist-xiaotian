package com.shareworx.ezfm.system.crop.pre.vo;

import org.springframework.web.multipart.MultipartFile;

public class PreCropVo{
	
	
	/** 企业编码 */
	String code;	
	/** 企业名称 */
	String name;	
	/** 联系地址 */
	String address;	
	/** 联系电话 */
	String phone;	
	/** 邮政编码 */
	String post_code;	
	/** 联系人 */
	String contact;	
	/** 邮箱 */
	String email;
	/**附件*/
	MultipartFile crop_file;
	
	public MultipartFile getCrop_file() {
		return crop_file;
	}
	public void setCrop_file(MultipartFile crop_file) {
		this.crop_file = crop_file;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
