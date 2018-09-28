package com.xzw.model;

/**
 * @Author: 谢志伟
 * @Description:
 * @Data: Created in 2018/9/27 14:06
 * @ModifiedBy:
 */
public class UploadMessage {
private int code;
private String msg;
private UploadMessageData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UploadMessageData getData() {
        return data;
    }

    public void setData(UploadMessageData data) {
        this.data = data;
    }

    public UploadMessage(int code, String msg, UploadMessageData data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
