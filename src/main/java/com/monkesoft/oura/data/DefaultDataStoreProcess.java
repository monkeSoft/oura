package com.monkesoft.oura.data;

import com.monkesoft.oura.annotation.DataStoreProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DefaultDataStoreProcess implements IDataStoreProcess {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean needProcess(Object object){
        Class<?> objectClass = object.getClass();
        DataStoreProcess dspAnnotation = AnnotationUtils.findAnnotation(objectClass, DataStoreProcess.class);
        if (Objects.nonNull(dspAnnotation)){
            return true;
        }
        return true;
    }

    private List<Field> getNeedProcessFields(Object object){
        Class<?> objectClass = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (objectClass != null){
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {
                DataStoreProcess fieldAnnotation = field.getAnnotation(DataStoreProcess.class);
                if (Objects.isNull(fieldAnnotation)){
                    continue;
                }
                fieldList.add(field);
            }

            objectClass = objectClass.getSuperclass();
        }

        return fieldList;
    }

    @Override
    public Object beforeTODB(Object object)  throws IllegalAccessException{
        List<Field> fieldList = getNeedProcessFields(object);
        for (Field field : fieldList){
            field.setAccessible(true);
            Object value = field.get(object);
            if (value instanceof BigDecimal){
//                BigDecimal value = (BigDecimal)object;
//                double doubleValue = value.add(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).movePointLeft(4).doubleValue();
//                field.set(result, BigDecimal.valueOf(doubleValue));
            }else if (value instanceof Integer){
                //TODO 定制Integer类型的加密算法
            }else if (value instanceof Long){
                //TODO 定制Long类型的加密算法
            }else if (value instanceof String){
                //TODO 定制String类型的加密算法
                String v1 = (String)object;
                field.set(object, v1.replace("000", ""));

            }

        }
        return object;
    }

    @Override
    public Object afterFromDB(Object object)  throws IllegalAccessException{
        if (object instanceof ArrayList) {
            ArrayList resultList = (ArrayList) object;
            //先判断列表中第一个是否需要处理，节省遍历时间。
            if (CollectionUtils.isEmpty(resultList) || !needProcess(resultList.get(0))){
                return object;
            }

            for (int i = 0; i < resultList.size(); i++) {
                processPO(resultList.get(i));
            }
        }else {
            if (needProcess(object)){
                processPO(object);
            }
        }
        return  object;

    }

    public void processPO(Object object)  throws IllegalAccessException{

        List<Field> fieldList = getNeedProcessFields(object);
        for (Field field : fieldList){
            field.setAccessible(true);
            Object value = field.get(object);
            if(value == null)
                continue;

            field.set(object, new DefaultDataProcessor().process(value));
            field.setAccessible(false);

        }
    }
}
