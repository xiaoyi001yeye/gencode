package com.riil.push.gencode.entity;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by weiyi on 2016/1/21.
 */
public class GenFileEntity implements Serializable {

    public GenFileEntity(BuildProjectContext context) {
        variables.put("context",context);
    }

    public GenFileEntity() {
    }

    private File genFile;

    private String vmTemplateName;

    private Map<String,Object> variables = Maps.newHashMap();

    public File getGenFile() {
        return genFile;
    }

    public void setGenFile(File genFile) {
        this.genFile = genFile;
    }

    public String getVmTemplateName() {
        return vmTemplateName;
    }

    public void setVmTemplateName(String vmTemplateName) {
        this.vmTemplateName = vmTemplateName;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    /*public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }*/

    public void putAllVariables(Map<String, Object> map){
        this.variables.putAll(map);
    }

    public void putVariables(String key,Object value){
        this.variables.put(key,value);
    }
}
