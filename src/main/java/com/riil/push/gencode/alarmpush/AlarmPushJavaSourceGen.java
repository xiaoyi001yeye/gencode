package com.riil.push.gencode.alarmpush;

import com.google.common.collect.Maps;
import com.riil.push.gencode.core.GencodeException;
import com.riil.push.gencode.core.IJavaSourceGenerate;
import com.riil.push.gencode.entity.BuildProjectContext;
import com.riil.push.gencode.entity.GenFileEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.Map;

import static com.riil.push.gencode.Constant.*;
import static com.riil.push.gencode.utils.VelocityUtil.writeFile;
import static java.io.File.separator;

/**
 * Created by weiyi on 2016/1/19.
 */
@Service("AlarmPushJavaSourceGen")
public class AlarmPushJavaSourceGen implements IJavaSourceGenerate {

    public void gen(BuildProjectContext context) throws GencodeException {
        genMainJava(context);
        genConfigJson(context);
    }

    private void genConfigJson(BuildProjectContext context) {
        String configFileName = "config.json";
        File configFile = new File(context.getResourcesRoot(),configFileName);
        GenFileEntity entity = new GenFileEntity(context);
        entity.setGenFile(configFile);
        entity.getVariables().put("fileName",configFileName);
        entity.setVmTemplateName("vm_template/alarm_push_config_json.vm");
        writeFile(entity);
    }

    private void genMainJava(BuildProjectContext context) {
        String className = String.format("%s%s", context.getPluginId(), "Service");
        File javaSourceFile = new File(context.getJavaSourceRoot(),
                String.format("%s%s%s%s", context.getPackageFilePath(),
                        separator,
                        className,
                        ".java"));
        GenFileEntity entity = new GenFileEntity(context);
        entity.setGenFile(javaSourceFile);
        entity.setVmTemplateName("vm_template/alarm_push_main_class.vm");
        entity.getVariables().putAll(getJavaVariables());
        entity.getVariables().put("className", className);
        writeFile(entity);
    }

    private Map<String, Object> getJavaVariables(){
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("packageBase", PACKAGE_BASE);
        variables.put("gencodeDate", DATE_FORMAT.format(new Date()));
        variables.put("author", GEN_CODE_AUTHOR);
        return variables;
    }


}
