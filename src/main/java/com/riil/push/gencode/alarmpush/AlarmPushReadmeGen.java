package com.riil.push.gencode.alarmpush;

import com.riil.push.gencode.core.GencodeException;
import com.riil.push.gencode.core.IReadmeGenerate;
import com.riil.push.gencode.entity.BuildProjectContext;
import com.riil.push.gencode.entity.GenFileEntity;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.riil.push.gencode.utils.VelocityUtil.writeFile;
import static java.io.File.separator;

/**
 * Created by wangchongyang on 2016/1/19.
 */
@Service("AlarmPushReadmeGen")
public class AlarmPushReadmeGen implements IReadmeGenerate {

    public void gen(BuildProjectContext context) throws GencodeException {
        String readmeFileName = "README";
        File readmeFile = new File(String.format("%s%s%s%s", context.getProjectRoot(), separator, readmeFileName, ".md"));
        GenFileEntity fileEntity = new GenFileEntity(context);
        fileEntity.setGenFile(readmeFile);
        fileEntity.setVmTemplateName("vm_template/alarm_push_readme.vm");
        writeFile(fileEntity);
    }


}
