package com.monkesoft.oura.mybatis.interceptor;

import com.monkesoft.oura.mybatis.IDBProcess;
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
import java.util.Properties;

/**
 * Mybatis 返回值加解密拦截器
 *
 * @author fraser
 * @date 2019-05-15 14:40
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})
})
@ConditionalOnProperty(value = "domain.decrypt", havingValue = "true")
@Component
public class ResultInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDBProcess dbProcess;

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
//            if (CollectionUtils.isEmpty(resultList) && needToDecrypt(resultList.get(0))){
                for (int i = 0; i < resultList.size(); i++) {
//                    encryptDecrypt.decrypt(resultList.get(i));
                    logger.info("211");

                    dbProcess.afterFromDB(resultList.get(i));
                }
//            }
            logger.info("222");
        }else {
//            if (needToDecrypt(result)){
//                encryptDecrypt.decrypt(result);
//            }
            logger.info("123232");

            dbProcess.afterFromDB(result);
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