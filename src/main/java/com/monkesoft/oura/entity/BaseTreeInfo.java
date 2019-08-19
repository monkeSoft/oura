package com.monkesoft.oura.entity;

/**
 * 树形结构的基础类
 */
public class BaseTreeInfo extends BaseInfo {

    /**
     * 父节点
     */
    private String parentId;


    public BaseTreeInfo() {
        super();
    }
    public BaseTreeInfo(String id,String name) {
        super(id,name);
    }

    public String getParentId() {
        return parentId;
    }

    public BaseTreeInfo setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
}
