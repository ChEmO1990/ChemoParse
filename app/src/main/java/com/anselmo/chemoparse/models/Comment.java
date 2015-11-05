package com.anselmo.chemoparse.models;

import java.util.Date;

/**
 * Created by Anselmo Hernandez on 11/4/15.
 */
public class Comment {
    private String objectId;
    private String name;
    private Date createdAt;
    private String sex;
    private int age;
    private String marital_status;

    public Comment() {}

    public Comment(String objectId, String name, Date createdAt, String sex, int age, String marital_status) {
        this.objectId = objectId;
        this.name = name;
        this.createdAt = createdAt;
        this.sex = sex;
        this.age = age;
        this.marital_status = marital_status;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
}
