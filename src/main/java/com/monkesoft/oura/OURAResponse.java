package com.monkesoft.oura;

public class OURAResponse<T> {

    private boolean success;
    private int code;
    private String desc;
    private T data;

    public OURAResponse<T> success(){
        this.setSuccess(true);
        return this;
    }
    public OURAResponse<T> fail(){
        this.setSuccess(false);
        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public OURAResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public OURAResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public OURAResponse<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public T getData() {
        return data;
    }

    public OURAResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
