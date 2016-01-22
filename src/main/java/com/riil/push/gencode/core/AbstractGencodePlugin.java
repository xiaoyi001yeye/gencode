package com.riil.push.gencode.core;

import com.google.common.base.Throwables;
import com.google.common.io.Files;
import com.riil.push.gencode.Constant;
import com.riil.push.gencode.GencodePluginService;
import com.riil.push.gencode.entity.BuildProjectContext;
import com.riil.push.gencode.entity.ProjectMetaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

/**
 * Created by weiyi on 2016/1/19.
 */
public abstract class AbstractGencodePlugin {

    @Autowired
    GencodePluginService service = null;

    private IJavaSourceGenerate javaSourceGenerate;

    private IReadmeGenerate readmeGenerate;

    private IBuildGen buildGen;

    private IFormatCodeGen formatCodeGen;

    private ISpringIocGenerate springIocGenerate;

    public AbstractGencodePlugin(){
        //TODO
    }

    public abstract String getPluginId();

    public abstract String getPluginDesc();

    public void build(ProjectMetaInfo projectMetaInfo){
        notNull(projectMetaInfo,"项目信息为空！");
        hasLength(projectMetaInfo.getProjectCode(),"项目编码为空！");
        hasLength(projectMetaInfo.getProjectName(),"项目名称为空！");
        hasLength(projectMetaInfo.getBmcVersion(), "现场bmc版本为空！");
        hasLength(projectMetaInfo.getJiraURL(),"二次开发需求单为空！");
        hasLength(projectMetaInfo.getSecDevVersion(),"二次开发版本信息为空！");
        hasLength(projectMetaInfo.getCharset(),"字符集编码为空！");
        BuildProjectContext buildProjectContext = new BuildProjectContext(projectMetaInfo,this.getPluginId(),this.getPluginDesc());
        try {
            if(null != javaSourceGenerate){
                javaSourceGenerate.gen(buildProjectContext);
            }
            if(null != springIocGenerate){
                springIocGenerate.gen(buildProjectContext);
            }
            if(null != formatCodeGen){
                formatCodeGen.gen(buildProjectContext);
            }
            if(null != readmeGenerate){
                readmeGenerate.gen(buildProjectContext);
            }
            if(null != buildGen){
                buildGen.gen(buildProjectContext);
            }
            System.out.print("build project complate!!!!");
        }catch (Throwable t){
            System.out.print("build project error!!!!");
            t.printStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append(Constant.DATE_FORMAT.format(new Date()));
            sb.append("build project error!!!!\n");
            sb.append(Throwables.getStackTraceAsString(t));
            File errorFile = new File(buildProjectContext.getProjectRoot(), "build_error.log");
            try {
                //FileSystemUtils.deleteRecursively(buildProjectContext.getProjectRoot());
                Files.createParentDirs(errorFile);
                Files.write(sb.toString().getBytes(), errorFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void register(){
        service.put(this.getPluginId(),this);
    }

    public void setJavaSourceGenerate(IJavaSourceGenerate javaSourceGenerate) {
        this.javaSourceGenerate = javaSourceGenerate;
    }

    public void setReadmeGenerate(IReadmeGenerate readmeGenerate) {
        this.readmeGenerate = readmeGenerate;
    }

    public void setSpringIocGenerate(ISpringIocGenerate springIocGenerate) {
        this.springIocGenerate = springIocGenerate;
    }

    public void setFormatCodeGen(IFormatCodeGen formatCodeGen) {
        this.formatCodeGen = formatCodeGen;
    }

    public void setBuildGen(IBuildGen buildGen) {
        this.buildGen = buildGen;
    }
}
