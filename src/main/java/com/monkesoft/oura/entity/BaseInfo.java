package com.monkesoft.oura.entity;

import com.monkesoft.oura.annotation.DataStoreProcess;

import java.io.Serializable;

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

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public BaseInfo setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
