package com.monkesoft.oura.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.monkesoft.oura.jsonview.DetailView;

/**
 * 人员信息类
 */
public class UserInfo extends BaseInfo {

    public static final int STATUS_LOCK = 2;
    public static final int STATUS_OFF = 0;

    public final static String EXT_GROUP="user";


    private int age;
    private int sex;
    private String email;
    private String phone;
    private String password;
    private String address;

    public UserInfo() {
        super();
    }
    public UserInfo(String id, String name) {
        super(id,name);
    }

    public int getAge() {
        return age;
    }

    public UserInfo setAge(int age) {
        this.age = age;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public UserInfo setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }
}
