package gencode.test;

import com.google.common.base.Charsets;
import com.riil.push.gencode.GencodePluginService;
import com.riil.push.gencode.core.AbstractGencodePlugin;
import com.riil.push.gencode.entity.ProjectMetaInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.Charset;

/**
 * Created by weiyi on 2016/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:MATE-INF/services.xml")
public class AlarmPushGencodeTest {

    @Autowired
    private GencodePluginService gencodePluginService;

    @Test
    public void testGenCode(){
        AbstractGencodePlugin plugin =
            gencodePluginService.getGencodePlugin("AlarmPush");
        ProjectMetaInfo projectMetaInfo = new ProjectMetaInfo ();
        projectMetaInfo.setBmcVersion("6.7.5");
        projectMetaInfo.setProjectCode("TjYanFFF");
        projectMetaInfo.setProjectName("测试");
        projectMetaInfo.setJiraURL("http://172.17.160.69:8080/browse/TOOLS-6");
        projectMetaInfo.setCharset(Charsets.UTF_8.toString());
        projectMetaInfo.setSecDevVersion("1.0.0");
        projectMetaInfo.setJiraURL("http://192.13.35.45/sdfhd");
        plugin.build(projectMetaInfo);
    }
}
