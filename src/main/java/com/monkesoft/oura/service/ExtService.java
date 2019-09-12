package com.monkesoft.oura.service;

import com.monkesoft.oura.entity.ExtInfo;
import com.monkesoft.oura.inter.IExtService;
import com.monkesoft.oura.mybatis.mapper.ExtMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExtService implements IExtService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void insertExt(String group,String objId,Map<String, String> extInfos) {
        //可以执行批量操作的sqlSession
        SqlSession openSession=sqlSessionFactory.openSession(ExecutorType.BATCH);

        //批量保存执行前时间
        long start=System.currentTimeMillis();
        try{
            ExtMapper mapper= openSession.getMapper(ExtMapper.class);
            Set<String> keySet = extInfos.keySet();
            for (Iterator<String> it = keySet.iterator();it.hasNext();) {\
                String key = it.next();
                String value = extInfos.get(key);
                mapper.insertExt(new ExtInfo(group,objId,key,value));
            }

            openSession.commit();
            long end=  System.currentTimeMillis();
            //批量保存执行后的时间
            System.out.println("执行时长"+(end-start));
            //批量 预编译sql一次==》设置参数==》10000次==》执行1次   677
            //非批量  （预编译=设置参数=执行 ）==》10000次   1121

        }finally{
            openSession.close();
        }
    }

    @Override
    public void updateExt(Map<String, String> extInfos) {

    }

    @Override
    public void deleteExt(String objId) {

    }

    @Override
    public Map<String, String> getExtOfObj(String group, String objId) {
        return null;
    }
}
