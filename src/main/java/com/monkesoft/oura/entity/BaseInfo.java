package com.monkesoft.oura.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.monkesoft.oura.jsonview.SimpleView;

import java.io.Serializable;

/**
 * 基础类
 */
public class BaseInfo implements Serializable {

    public static final int STATUS_ON = 1;
    public static final int STATUS_OFF = 0;

    @JsonView(SimpleView.class)
    private String id;
    @JsonView(SimpleView.class)
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
