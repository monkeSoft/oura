package com.monkesoft.oura.service;

import com.monkesoft.oura.entity.ExtInfo;
import com.monkesoft.oura.inter.IExtService;
import com.monkesoft.oura.mybatis.mapper.ExtMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ExtService implements IExtService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    ExtMapper extMapper;

    @Override
    public void insertExt(String group,String objId,Map<String, String> extInfos) {
        //可以执行批量操作的sqlSession
        SqlSession openSession=sqlSessionFactory.openSession(ExecutorType.BATCH);
        try{
            ExtMapper mapper= openSession.getMapper(ExtMapper.class);
            Set<String> keySet = extInfos.keySet();
            for (Iterator<String> it = keySet.iterator();it.hasNext();) {
                String key = it.next();
                String value = extInfos.get(key);
                mapper.insertExt(new ExtInfo(group,objId,key,value));
            }

            openSession.commit();
        }finally{
            openSession.close();
        }
    }

    @Override
    public void updateExt(String group,String objId,Map<String, String> extInfos) {
        SqlSession openSession=sqlSessionFactory.openSession(ExecutorType.BATCH);
        try{
            ExtMapper mapper= openSession.getMapper(ExtMapper.class);
            Set<String> keySet = extInfos.keySet();
            for (Iterator<String> it = keySet.iterator();it.hasNext();) {
                String key = it.next();
                String value = extInfos.get(key);
                mapper.updateExt(new ExtInfo(group,objId,key,value));
            }

            openSession.commit();
        }finally{
            openSession.close();
        }
    }

    @Override
    public void deleteExt(String group,String objId) {
        extMapper.deleteExt(group,objId);
    }

    @Override
    public void deleteExt(String group,String objId,String extFieldId) {
        extMapper.deleteSingleExt(group,objId,extFieldId);

    }

    @Override
    public List<ExtInfo> getExtOfObj(String group, String objId) {
        return extMapper.getExtOfObj(group,objId);
    }
}
