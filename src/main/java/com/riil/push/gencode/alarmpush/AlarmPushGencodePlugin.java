package com.riil.push.gencode.alarmpush;

import com.riil.push.gencode.core.AbstractGencodePlugin;
import com.riil.push.gencode.core.IBuildGen;
import com.riil.push.gencode.core.IFormatCodeGen;
import com.riil.push.gencode.core.IJavaSourceGenerate;
import com.riil.push.gencode.core.IReadmeGenerate;
import com.riil.push.gencode.core.ISpringIocGenerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by sms on 2016/1/19.
 */
@Service
public class AlarmPushGencodePlugin extends AbstractGencodePlugin {

    @Override
    public String getPluginId() {
        return "AlarmPush";
    }

    @Override
    public String getPluginDesc() {
        return "告警推送";
    }

    @Autowired
    @Qualifier("AlarmPushReadmeGen")
    @Override
    public void setReadmeGenerate(IReadmeGenerate readmeGenerate) {
        super.setReadmeGenerate(readmeGenerate);
    }

    @Autowired
    @Qualifier("AlarmPushJavaSourceGen")
    @Override
    public void setJavaSourceGenerate(IJavaSourceGenerate javaSourceGenerate) {
        super.setJavaSourceGenerate(javaSourceGenerate);
    }

    @Autowired
    @Qualifier("AlarmPushSpringIocGen")
    @Override
    public void setSpringIocGenerate(ISpringIocGenerate springIocGenerate) {
        super.setSpringIocGenerate(springIocGenerate);
    }

    @Autowired
    @Qualifier("DefaultJavaFormatGen")
    @Override
    public void setFormatCodeGen(IFormatCodeGen formatCodeGen) {
        super.setFormatCodeGen(formatCodeGen);
    }

    @Autowired
    @Qualifier("DefaultBuildGen")
    @Override
    public void setBuildGen(IBuildGen buildGen) {
        super.setBuildGen(buildGen);
    }
}
