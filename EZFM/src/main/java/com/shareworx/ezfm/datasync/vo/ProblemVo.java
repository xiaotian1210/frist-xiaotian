package com.shareworx.ezfm.datasync.vo;

/**
 * Created by zhi.zhang on 2017/7/11.
 */
public class ProblemVo {
    String resources_id;
    //资源编码
    private String resources_code;
    //资源类型
    private String resources_type;
    //资源名称
    private String resources_name;
    //父资源id
    private String parent_id;
    //数据来源
    private String source_from;
    //在数据来源系统中的唯一标示
    private String  source_id;

    public String getResources_id() {
        return resources_id;
    }

    public void setResources_id(String resources_id) {
        this.resources_id = resources_id;
    }

    public String getResources_code() {
        return resources_code;
    }

    public void setResources_code(String resources_code) {
        this.resources_code = resources_code;
    }

    public String getResources_type() {
        return resources_type;
    }

    public void setResources_type(String resources_type) {
        this.resources_type = resources_type;
    }

    public String getResources_name() {
        return resources_name;
    }

    public void setResources_name(String resources_name) {
        this.resources_name = resources_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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
