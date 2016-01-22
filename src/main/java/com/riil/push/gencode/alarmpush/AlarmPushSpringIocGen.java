package com.riil.push.gencode.alarmpush;

import com.google.common.collect.Maps;
import com.riil.push.gencode.core.GencodeException;
import com.riil.push.gencode.core.ISpringIocGenerate;
import com.riil.push.gencode.entity.BuildProjectContext;
import com.riil.push.gencode.entity.GenFileEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

import static com.riil.push.gencode.utils.VelocityUtil.writeFile;
import static java.io.File.separator;

@Service("AlarmPushSpringIocGen")
public class AlarmPushSpringIocGen implements ISpringIocGenerate {

    public void gen(BuildProjectContext context) throws GencodeException {
        GenFileEntity fileEntity = new GenFileEntity(context);
        String serviceFileName = String.format("%s%s", context.getPluginId(), "riil_service");
        File iocFile =
            new File(String.format("%s%s%s%s", context.getMetaInfDir(), separator,
                serviceFileName, ".xml"));
        fileEntity.setGenFile(iocFile);
        fileEntity.setVmTemplateName("vm_template/alarm_push_service_xml.vm");
        Map<String, Object> serviceVar = Maps.newHashMap();
        serviceVar.put("serviceName", serviceFileName);
        serviceVar.put("packageNamePath", context.getPackageNamePath());
        String className = String.format("%s%s",context.getPluginId(), "Service");
        serviceVar.put("className", className);
        fileEntity.putAllVariables(serviceVar);
        writeFile(fileEntity);
    }


}
