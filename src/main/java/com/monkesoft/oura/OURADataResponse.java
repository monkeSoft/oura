package com.monkesoft.oura;

public class OURADataResponse<T> extends OURAResponse{

    private T data;

    public T getData() {
        return data;
    }

    public OURADataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
