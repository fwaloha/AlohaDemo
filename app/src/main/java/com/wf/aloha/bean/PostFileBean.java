package com.wf.aloha.bean;

import java.io.Serializable;

public class PostFileBean extends BaseRtfBean{

    /**
     * resCode : 0000
     * resMsg : 成功
     */

    private String resCode;
    private String resMsg;

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
