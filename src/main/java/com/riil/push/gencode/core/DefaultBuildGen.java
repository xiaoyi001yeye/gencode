package com.riil.push.gencode.core;

import com.google.common.collect.Maps;
import com.riil.push.gencode.ServerEnv;
import com.riil.push.gencode.entity.BuildProjectContext;
import com.riil.push.gencode.entity.GenFileEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;
import static com.riil.push.gencode.utils.VelocityUtil.writeFile;
import static java.io.File.separator;


/**
 * DefaultBuildGen.<br/>
 * Created by weiyi on 2016/1/19.
 */
@Service("DefaultBuildGen")
public class DefaultBuildGen implements IBuildGen {
    /**
     * @param context BuildProjectContext
     * @throws GencodeException GencodeException
     */
    public void gen(BuildProjectContext context) throws GencodeException {
        Map<String, Object> variables = getVariables(context);
        GenFileEntity gradleFileEntity = new GenFileEntity(context);
        gradleFileEntity.setGenFile(new File(context.getProjectRoot(),"build.gradle"));
        gradleFileEntity.putAllVariables(variables);
        gradleFileEntity.setVmTemplateName("vm_template/default_build_gradle.vm");
        writeFile(gradleFileEntity);
        GenFileEntity commandFileEntity = new GenFileEntity(context);
        commandFileEntity.setGenFile(new File(context.getProjectRoot(),"build.bat"));
        commandFileEntity.putAllVariables(variables);
        commandFileEntity.setVmTemplateName("vm_template/run_gradle_windows.vm");
        writeFile(commandFileEntity);
        doCommand(commandFileEntity.getGenFile());
    }

    private void doCommand(File commandFile) {
        try {
            Process p = Runtime.getRuntime().exec(commandFile.getCanonicalPath());//调用本地记事本程序
            InputStream stderr = p.getInputStream(); // 获取输入流
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) { // 打印出命令执行的结果
                System.out.println(line);
            }
            int sing = p.waitFor();
            if(sing!=0){
                throw new GencodeException("Do Command Error!!!");
            }
        }catch (Exception e){
            propagate(e);
        }
    }

    private Map<String,Object> getVariables(BuildProjectContext context) {
        Map<String,Object> vars = Maps.newHashMap();
        vars.put("jdkPath",ServerEnv.getVariable("jdkPath"));
        vars.put("gradlePath",ServerEnv.getVariable("gradlePath"));
        vars.put("mavenRepository",ServerEnv.getVariable("mavenRepository"));
        vars.put("repository",ServerEnv.TARGET_PATH+separator+"repository");
        vars.put("srcDirName", String.format("%s\\\\%s",
                context.getJavaSourceRoot().getParentFile().getName(),
                context.getJavaSourceRoot().getName()));
        vars.put("resourceDirName", String.format("%s\\\\%s",
                context.getResourcesRoot().getParentFile().getName(),
                context.getResourcesRoot().getName()));
        vars.put("targetDirName", context.getTargetRoot().getName());
        try {
            vars.put("projectRoot", context.getProjectRoot().getCanonicalPath());
        } catch (IOException e) {
            propagate(e);
        }
        return vars;
    }
}
