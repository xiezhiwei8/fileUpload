package com.xzw.model;

/**
 * @Author: 谢志伟
 * @Description:
 * @Data: Created in 2018/9/27 14:22
 * @ModifiedBy:
 */
public class UploadMessageData {
    private String path;
    private String size;

    public UploadMessageData(String path, String size) {
        this.path = path;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
