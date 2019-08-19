package com.monkesoft.oura;

public class OURAResponse {

    private boolean success;
    private int code;
    private String desc;

    public OURAResponse success(){
        this.setSuccess(true);
        return this;
    }
    public OURAResponse fail(){
        this.setSuccess(false);
        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public OURAResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public OURAResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public OURAResponse setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
