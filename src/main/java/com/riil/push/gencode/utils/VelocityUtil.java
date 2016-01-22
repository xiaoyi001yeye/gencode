package com.riil.push.gencode.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.riil.push.gencode.core.GencodeException;
import com.riil.push.gencode.entity.GenFileEntity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

/**
 * Created by weiyi on 2016/1/19.
 */
public class VelocityUtil {

    static {
        Properties p = new Properties();
        p.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
    }

    /**
     * @param templateName 模版名称
     * @param context 变量map
     * @param file 写入的文件
     * @throws FileNotFoundException FileNotFoundException
     */
    public static void writeFile(final String templateName,final Map<String,Object> context,final File file) throws GencodeException {
        checkNotNull(templateName);
        checkNotNull(file);
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate(templateName,
                Charsets.UTF_8.toString(),
                new VelocityContext(context),
                writer)
        ;
        try {
            Files.createParentDirs(file);
            Files.write(writer.toString().getBytes(),file);
        }catch (IOException e){
            propagate(e);
        }
    }

    public static void writeFile(GenFileEntity fileEntity) throws GencodeException {
        checkNotNull(fileEntity);
        writeFile(fileEntity.getVmTemplateName(), fileEntity.getVariables(), fileEntity.getGenFile());
    }
}
