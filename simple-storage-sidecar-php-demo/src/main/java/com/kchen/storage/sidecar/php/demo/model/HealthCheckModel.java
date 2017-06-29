package com.kchen.storage.sidecar.php.demo.model;

/**
 * Created by kchen on 2017/6/29.
 */
public class HealthCheckModel {

    private String status = "UP";

    public HealthCheckModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
