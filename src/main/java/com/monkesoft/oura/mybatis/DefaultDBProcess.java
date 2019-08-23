package com.monkesoft.oura.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DefaultDBProcess  implements IDBProcess{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public <T> T beforeTODB(T object)  throws IllegalAccessException{
        Class<?> parameterObjectClass = object.getClass();
        Field[] declaredFields = parameterObjectClass.getDeclaredFields();
        for (Field field : declaredFields){
//            EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
//            if (Objects.isNull(annotation)){
//                continue;
//            }
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
    public <T> T afterFromDB(T object)  throws IllegalAccessException{
        Class<?> objectClass = object.getClass();

        List<Field> fieldList = new ArrayList<>();
        Class<?> class1 = objectClass;
        while (objectClass != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(objectClass.getDeclaredFields())));
            objectClass = objectClass.getSuperclass();
        }
//        Field[] declaredFields = objectClass.getFields();
        for (Field field : fieldList){
//            EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
//            if (Objects.isNull(annotation)){
//                continue;
//            }
            field.setAccessible(true);
            Object value = field.get(object);
            if(value == null)
                continue;

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

                String v1 = (String)value;
                field.set(object, v1+"****");

            }

            field.setAccessible(false);

        }
        return object;
    }
}
