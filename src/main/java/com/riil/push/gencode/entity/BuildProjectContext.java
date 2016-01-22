package com.riil.push.gencode.entity;

import com.riil.push.gencode.ServerEnv;

import java.io.File;

import static com.riil.push.gencode.Constant.*;
import static java.io.File.separator;

/**
 * Created by weiyi on 2016/1/19.
 */
public class BuildProjectContext {


    /**
     * 项目信息
     */
    private ProjectMetaInfo projectMetaInfo;
    /**
     * 插件id
     */
    private String pluginId;
    /**
     * 插件描述
     */
    private String pluginDesc;


    /**
     * 项目根路径
     */
    private File projectRoot;

    /**
     * java源代码路径
     */
    private File javaSourceRoot;

    /**
     * 资源路径
     */
    private File resourcesRoot;

    /**
     * 生成目标路径
     */
    private File targetRoot;
    /*
    *项目相对路径.
     */
    private String projectFullName;

    /**
     * package相对路径
     */
    private String packageFilePath;
    /**
     * Spring核心配置文件路径
     */
    private File metaInfDir;

    /**
     * package名称
     */
    private String packageNamePath;


    /**
     * 构造项目上下文对象的构造方法.
     * @param projectMetaInfo 项目信息
     * @param pluginId 插件id
     * @param pluginDesc 插件描述
     */
    public BuildProjectContext(ProjectMetaInfo projectMetaInfo,String pluginId,String pluginDesc) {
        this.projectMetaInfo = projectMetaInfo;
        this.pluginId = pluginId;
        this.pluginDesc = pluginDesc;
        projectFullName = String.format("%s-%s",
                pluginId,
                projectMetaInfo.getProjectCode())
        ;
        projectRoot = new File(String.format("%s%s%s%s%s",
                ServerEnv.TARGET_PATH,
                separator,
                projectFullName,
                separator,
                projectMetaInfo.getSecDevVersion()))
        ;
        javaSourceRoot = new File(projectRoot, String.format("%s%ssrc",
                SOURCE_DIRECTORY_NAME,
                separator
        ));
        resourcesRoot = new File(projectRoot, String.format("%s%sresources",
                SOURCE_DIRECTORY_NAME,
                separator
        ));
        targetRoot = new File(projectRoot, TARGET_DIRECTORY_NAME);
        packageFilePath = PACKAGE_BASE_PATH + pluginId.toLowerCase();
        packageNamePath = String.format("%s.%s", PACKAGE_BASE, pluginId.toLowerCase());
        metaInfDir = new File(resourcesRoot, META_INF_DIR);
    }

    public ProjectMetaInfo getProjectMetaInfo() {
        return projectMetaInfo;
    }

    /**
     * @return projectRoot
     */
    public File getProjectRoot() {
        return projectRoot;
    }

    /**
     * @return javaSourceRoot
     */
    public File getJavaSourceRoot() {
        return javaSourceRoot;
    }

    /**
     * @return metaInfoDir
     */
    public File getMetaInfDir() {
        return metaInfDir;
    }

    /**
     * @return pluginId
     */
    public String getPluginId() {
        return pluginId;
    }

    /**
     * @return pluginDesc
     */
    public String getPluginDesc() {
        return pluginDesc;
    }

    /**
     * @return projectFullName
     */
    public String getProjectFullName() {
        return projectFullName;
    }

    public String getPackageFilePath() {
        return packageFilePath;
    }

    public String getPackageNamePath() {
        return packageNamePath;
    }

    public File getTargetRoot() {
        return targetRoot;
    }

    public File getResourcesRoot() {
        return resourcesRoot;
    }
}
