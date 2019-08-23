package com.monkesoft.oura.mybatis;

/**
 * 数据处理接口
 */
public interface IDBProcess {

    /**
     * 数据入库前处理，如加密
     * @param object
     * @param <T>
     * @return
     */
    public <T> T beforeTODB(T object) throws IllegalAccessException;

    /**
     * 数据捞出来后的处理，如解密
     * @param object
     * @param <T>
     * @return
     */
    public <T> T afterFromDB(T object) throws IllegalAccessException;
}
