package com.monkesoft.oura.data;

import java.math.BigDecimal;

public class DefaultDataProcessor implements IDataProcessor {

    @Override
    public Object process(Object value) {
        if (value instanceof BigDecimal){
//                BigDecimal value = (BigDecimal)object;
//                double doubleValue = value.add(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).movePointLeft(4).doubleValue();
//                field.set(result, BigDecimal.valueOf(doubleValue));
        }else if (value instanceof Integer){
            return processInt((Integer)value);
        }else if (value instanceof Long){
            return processLong((Long)value);
        }else if (value instanceof String){
            return processString((String)value);

        }
        return null;
    }

    private String processString(String value){
        return value+"****";
    }

    private Long processLong(Long value) {
        return value;
    }

    private Integer processInt(Integer value) {
        return value;
    }
}
