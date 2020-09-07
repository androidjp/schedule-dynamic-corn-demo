package com.example.scheduledemo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class GlobalAppConfig implements Serializable {
    public static final long SerializeUID = 1597820836546L;
    @Id
    private String id;
    private String app;
    private String group;
    private String key;
    private String value;
    private String classType;

    public GlobalAppConfig(String key, String value, String classType) {
        this.key = key;
        this.value = value;
        this.classType = classType;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
