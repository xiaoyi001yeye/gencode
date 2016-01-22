package com.riil.push.gencode;

import com.google.common.collect.Maps;
import com.riil.push.gencode.core.AbstractGencodePlugin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by sms on 2016/1/19.
 */
@Service
public class GencodePluginService {

    private Map<String,AbstractGencodePlugin> pluginMap = Maps.newConcurrentMap();

    public void put(String pluginId,AbstractGencodePlugin gencodePlugin){
        pluginMap.put(pluginId,gencodePlugin);
    }

    public AbstractGencodePlugin getGencodePlugin(String pluginId){
        return pluginMap.get(pluginId);
    }

}
