package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.ExtInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExtMapper {

    void insertExt(ExtInfo extInfo);
    void updateExt(ExtInfo extInfo);
    void deleteExt(String group,String objId);
    void deleteSingleExt(String group,String objId,String extFieldId);

     List<ExtInfo> getExtOfObj(String group, String objId);
}
