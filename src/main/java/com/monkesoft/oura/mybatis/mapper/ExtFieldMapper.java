package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.ExtFieldInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtFieldMapper {

    void insertExtField(ExtFieldInfo extFieldInfo);

    void updateExtField(ExtFieldInfo extFieldInfo);

    void deleteExtField(String group,String extFieldId);

    List<ExtFieldInfo> getExtFieldsByGroup(String group);

}
