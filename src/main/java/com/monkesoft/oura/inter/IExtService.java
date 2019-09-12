package com.monkesoft.oura.inter;

import java.util.Map;

public interface IExtService {

    public void insertExt(String group,String objId,Map<String,String> extInfos);
    public void updateExt(String group,String objId,Map<String,String> extInfos);
    public void deleteExt(String group,String objId,String extFieldId);

    public Map<String,String> getExtOfObj(String group,String objId);

}
