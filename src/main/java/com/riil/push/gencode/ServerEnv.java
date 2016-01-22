package com.riil.push.gencode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.riil.push.gencode.core.GencodeException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.io.File.separator;

/**
 * Created by weiyi on 2016/1/19.
 */
public class ServerEnv {

    public static String APP_ROOT_PATH;
    public static String TARGET_PATH;

    private static JSONObject SERVER_ENV_JSON_OBJECT;

    static {
        try {
            URL url = Resources.getResource("server_env.json");
            String jsonString = Resources.toString(url, Charsets.UTF_8);
            SERVER_ENV_JSON_OBJECT = JSONObject.parseObject(jsonString);
            APP_ROOT_PATH = SERVER_ENV_JSON_OBJECT.getString("appPath");
            TARGET_PATH = String.format("%s%s%s", APP_ROOT_PATH, separator,"target");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String getVariable(String key){
        return SERVER_ENV_JSON_OBJECT.getString(key);
    }

}
