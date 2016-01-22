package com.riil.push.gencode;

import static java.io.File.separator;

import java.text.SimpleDateFormat;

/**
 * Created by sms on 2016/1/19.
 */
public class Constant {

    // public static final String SPRING_FILE_PATH = "";

    public static final String PACKAGE_BASE = "com.riil";

    public static final String PACKAGE_BASE_PATH = String.format("%scom%sriil%s", separator, separator, separator);

    public static final String SOURCE_DIRECTORY_NAME = "source";

    public static final String TARGET_DIRECTORY_NAME = "target";

    public static final String GEN_CODE_AUTHOR = "GenCoder";
    
    public static final String META_INF_DIR = "META-INF";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
