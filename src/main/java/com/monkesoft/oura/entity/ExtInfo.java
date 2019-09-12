package com.monkesoft.oura.entity;

import lombok.Data;

/**
 * 扩展信息 group+objId+extFileId 唯一
 */
@Data
public class ExtInfo {
    //分组类别
    private String group;

    //对象id
    private String objId;

    //扩展字段ID
    private String extFileId;

    //值
    private String value;

    public ExtInfo(){};
    public ExtInfo(String group,String objId,String extFileId,String value){
        this.group = group;
        this.objId = objId;
        this.extFileId = extFileId;
        this.value = value;
    };

}
