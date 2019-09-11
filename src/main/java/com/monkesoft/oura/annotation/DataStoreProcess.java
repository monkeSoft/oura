package com.monkesoft.oura.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataStoreProcess {
//
//    @AliasFor("processor")
//    Class<? extends IDataProcessor> value() default IDataProcessor.class;
//
//    @AliasFor("value")
//    Class<? extends IDataProcessor> processor() default IDataProcessor.class;
}
