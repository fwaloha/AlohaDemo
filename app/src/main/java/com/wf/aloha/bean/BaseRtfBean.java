package com.wf.aloha.bean;

import java.io.Serializable;

public class BaseRtfBean<T> implements Serializable {

    /**
     * resCode : 0000
     * resMsg : 成功
     */

    private String resCode;
    private String resMsg;
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
