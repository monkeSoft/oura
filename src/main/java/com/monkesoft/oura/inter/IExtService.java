package com.monkesoft.oura.inter;

import com.monkesoft.oura.entity.ExtInfo;

import java.util.List;
import java.util.Map;

/**
 * 对象扩展信息服务接口
 */
public interface IExtService {

    public void insertExt(String group,String objId,Map<String,String> extInfos);
    public void updateExt(String group,String objId,Map<String,String> extInfos);
    public void deleteExt(String group,String objId);
    public void deleteExt(String group,String objId,String extFieldId);

    public List<ExtInfo> getExtOfObj(String group, String objId);

}
