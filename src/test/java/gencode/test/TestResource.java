package gencode.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by weiyi on 2016/1/20.
 */
public class TestResource {

    @Test
    public void testJsonResource() throws IOException {
        String json = Resources.toString(Resources.getResource("server_env.json"), Charsets.UTF_8);
        System.out.println("json = " + JSON.parse(json));
    }

    @Test
    public void testVmResource()throws IOException{
        //String vm = Resources.toString(Resources.getResource("vm_template/alarm_push_main_class.vm"), Charsets.UTF_8);
        //System.out.println("vm = " + vm);
        Properties p = new Properties();
        p.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
        Template template = Velocity.getTemplate("/vm_template/alarm_push_main_class.vm");
        StringWriter stringWriter = new StringWriter();
        //template.merge(new VelocityContext(), Files.newWriter(new File("d:\\aaa.txt"),Charsets.UTF_8));
        template.merge(new VelocityContext(), stringWriter);
        System.out.println("stringWriter = " + stringWriter);
        Files.write(stringWriter.toString().getBytes(),new File("d:\\aaa.txt"));
        //System.out.println("template.getData() = " + template.getData());
    }
}
