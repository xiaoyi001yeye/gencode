package gencode.test;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.riil.push.gencode.ServerEnv;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.*;
import java.util.Map;

import static java.io.File.separator;

/**
 * Created by weiyi on 2016/1/20.
 */
public class GradleTest {

    @Test
    public void testGradle() throws Exception {
        System.err.println("GradleTest.testGradle bgin");
        Class.forName("com.riil.push.gencode.utils.VelocityUtil");
        Map<String,String> c = Maps.newHashMap();
        c.put("jdkPath",ServerEnv.getVariable("jdkPath"));
        c.put("gradlePath",ServerEnv.getVariable("gradlePath"));
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("vm_template/run_gradle_windows.vm",
                Charsets.UTF_8.toString(),
                new VelocityContext(c),
                writer)
        ;
        String command = String.format("%s%srun.bat",ServerEnv.TARGET_PATH,separator);
        Files.write(writer.toString().getBytes(),new File(command));
        Process p = Runtime.getRuntime().exec(command);//调用本地记事本程序
        InputStream stderr = p.getInputStream(); // 获取输入流
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) { // 打印出命令执行的结果
            System.out.println(line);
        }
        p.waitFor();
        System.err.println("return code: " + p.exitValue());
        System.err.println("GradleTest.testGradle end");
    }
}
