package com.monkesoft.oura.entity;

import com.monkesoft.oura.annotation.DataStoreProcess;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础类
 */
public class BaseInfo implements Serializable {

    public static final int STATUS_ON = 1;
    public static final int STATUS_OFF = 0;


    private String id;

    @DataStoreProcess
    private String name;

    private int status = STATUS_ON;

    private String desc;

    /**
     * 扩展信息
     */
    private Map<String,String> ext;

    public BaseInfo() {
    }
    public BaseInfo(String id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public BaseInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseInfo setName(String name) {
        this.name = name;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public BaseInfo setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public BaseInfo setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public BaseInfo addExt(String key, String value) {
        if (ext == null)
            ext = new HashMap<>();

        ext.put(key,value);

        return this;
    }

    public BaseInfo setExt(Map<String,String> ext) {
        this.ext = ext;
        return this;
    }

    public Map<String,String> getExt() {
        return this.ext;
    }


}
