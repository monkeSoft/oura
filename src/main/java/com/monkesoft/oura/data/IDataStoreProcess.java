package com.monkesoft.oura.data;

/**
 * 数据处理接口
 */
public interface IDataStoreProcess {

    public boolean needProcess(Object object);

    /**
     * 数据入库前处理，如加密
     * @param object
     * @return
     */
    public Object beforeTODB(Object object) throws IllegalAccessException;

    /**
     * 数据捞出来后的处理，如解密
     * @param object
     * @return
     */
    public Object afterFromDB(Object object) throws IllegalAccessException;
}
