package com.kchen.storage.common.util.web.entity;

/**
 * Created by ky.chen on 2016/11/1.
 * 每个请求保存的trace信息
 */
public class TraceInfo {

    private String uuid;    //请求对应的uuid

    public TraceInfo() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
