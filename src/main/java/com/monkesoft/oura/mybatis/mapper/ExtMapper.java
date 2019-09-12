package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.ExtInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ExtMapper {
     void insertExt(ExtInfo extInfo);
     void updateExt(Map<String,String> extInfos);
     void deleteExt(String objId);

     Map<String,String> getExtOfObj(String group,String objId);
}
