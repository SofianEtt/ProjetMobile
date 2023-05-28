package com.androiddev.recruitmentsystem.Modules;

public class ManagerModule {
    public String name;
    public String phone;
    public String password;
    public String gender;
    public String cname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCregis() {
        return cregis;
    }

    public void setCregis(String cregis) {
        this.cregis = cregis;
    }

    public ManagerModule() {
    }

    public ManagerModule(String name, String phone, String password, String gender, String cname, String cregis) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.cname = cname;
        this.cregis = cregis;
    }

    public String cregis;
}
