package com.riil.push.gencode.core;

import com.riil.push.gencode.entity.BuildProjectContext;

/**
 * Created by sms on 2016/1/19.
 */
public interface ISuperGenerate {

    void gen(BuildProjectContext context) throws GencodeException;
}
