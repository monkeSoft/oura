package com.monkesoft.oura.mybatis.interceptor;

import com.monkesoft.oura.data.IDataStoreProcess;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Mybatis 返回值加解密拦截器
 *
 * @author fraser
 * @date 2019-05-15 14:40
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})
})
@ConditionalOnProperty(value = "oura.crypt", havingValue = "true")
@Component
public class ResultInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDataStoreProcess dbProcess;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("拦截器ResultInterceptor");
        Object result = invocation.proceed();
        logger.info(result.toString());
        if (Objects.isNull(result)){
            return null;
        }

        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            //先判断列表中第一个是否需要处理，节省遍历时间。
            if (CollectionUtils.isEmpty(resultList) || !dbProcess.needProcess(resultList.get(0))){
                return result;
            }

            for (int i = 0; i < resultList.size(); i++) {
                dbProcess.afterFromDB(resultList.get(i));
            }
        }else {
            if (dbProcess.needProcess(result)){
                dbProcess.afterFromDB(result);
            }
        }
        return result;
    }

    public boolean needToDecrypt(Object object){
        Class<?> objectClass = object.getClass();
//        EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptDecryptClass.class);
//        if (Objects.nonNull(encryptDecryptClass)){
//            return true;
//        }
        return false;
    }

}