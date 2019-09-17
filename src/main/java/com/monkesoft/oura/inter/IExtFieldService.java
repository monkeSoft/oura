package com.monkesoft.oura.inter;

import com.monkesoft.oura.entity.ExtFieldInfo;

import java.util.List;

/**
 * 扩展字段维护服务接口
 */
public interface IExtFieldService {

    //增
    public void insertExtField(ExtFieldInfo extFieldInfo);

    //改
    public void updateExtField(ExtFieldInfo extFieldInfo);

    //删
    public void deleteExtField(String group,String extFieldId);

    //查
    public List<ExtFieldInfo> getExtFieldsByGroup(String group);
}
