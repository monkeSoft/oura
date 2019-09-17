package com.monkesoft.oura.service;

import com.monkesoft.oura.entity.ExtFieldInfo;
import com.monkesoft.oura.inter.IExtFieldService;
import com.monkesoft.oura.mybatis.mapper.ExtFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtFieldService implements IExtFieldService {

    @Autowired
    ExtFieldMapper extFieldMapper;

    @Override
    public void insertExtField(ExtFieldInfo extFieldInfo) {
        extFieldMapper.insertExtField(extFieldInfo);
    }

    @Override
    public void updateExtField(ExtFieldInfo extFieldInfo) {
        extFieldMapper.updateExtField(extFieldInfo);
    }

    @Override
    public void deleteExtField(String group, String extFieldId) {
        extFieldMapper.deleteExtField(group,extFieldId);
    }

    @Override
    public List<ExtFieldInfo> getExtFieldsByGroup(String group) {
        return extFieldMapper.getExtFieldsByGroup(group);
    }
}
