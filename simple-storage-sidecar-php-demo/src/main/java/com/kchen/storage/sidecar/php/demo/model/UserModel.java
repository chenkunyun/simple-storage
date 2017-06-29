package com.kchen.storage.sidecar.php.demo.model;

/**
 * Created by kchen on 2017/6/29.
 */
public class UserModel {

    private Integer age = 22;
    private String name = "chen";

    public UserModel() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
