package com.shareworx.ezfm.datasync.vo;

/**
 * Created by zhi.zhang on 2017/7/11.
 */
public class DeviceVo {

    //设备
    private String device_id;
    //设备名称
    private String name;
    //设备编码
    private String fm_code;
    //资源名称
    private String rm_id;
    //设备分类id
    private String csi_id;
    //设备惯用名
    private String use_name;
    //使用部门id
    private String use_dept;
    //维护部门id
    private String service_dept;
    //数据来源
    private String source_from;
    private String source_id;


    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFm_code() {
        return fm_code;
    }

    public void setFm_code(String fm_code) {
        this.fm_code = fm_code;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getCsi_id() {
        return csi_id;
    }

    public void setCsi_id(String csi_id) {
        this.csi_id = csi_id;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_dept() {
        return use_dept;
    }

    public void setUse_dept(String use_dept) {
        this.use_dept = use_dept;
    }

    public String getService_dept() {
        return service_dept;
    }

    public void setService_dept(String service_dept) {
        this.service_dept = service_dept;
    }

    public String getSource_from() {
        return source_from;
    }

    public void setSource_from(String source_from) {
        this.source_from = source_from;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }
}
