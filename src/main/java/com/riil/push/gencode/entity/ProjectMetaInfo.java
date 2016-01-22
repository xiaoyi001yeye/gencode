package com.riil.push.gencode.entity;

/**
 * 二次开发生成项目信息
 * Created by weiyi on 2016/1/19.
 */
public class ProjectMetaInfo {

    /**
     * @projectCode 二次开发项目编码
     */
    private String projectCode;
    /**
     * @projectName 二次开发项目名称
     */
    private String projectName;

    /**
     * @bmcVersion 现场bmc版本信息
     */
    private String bmcVersion;

    /**
     * @jiraURL 二次开发需求单
     */
    private String jiraURL;

    /**
     * @secDevVersion 二次开发版本信息
     */
    private String secDevVersion;

    /**
     * @charset 字符集编码
     */
    private String charset;


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBmcVersion() {
        return bmcVersion;
    }

    public void setBmcVersion(String bmcVersion) {
        this.bmcVersion = bmcVersion;
    }

    public String getJiraURL() {
        return jiraURL;
    }

    public void setJiraURL(String jiraURL) {
        this.jiraURL = jiraURL;
    }

    public String getSecDevVersion() {
        return secDevVersion;
    }

    public void setSecDevVersion(String secDevVersion) {
        this.secDevVersion = secDevVersion;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
