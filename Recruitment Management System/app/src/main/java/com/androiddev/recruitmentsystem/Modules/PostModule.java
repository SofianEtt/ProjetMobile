package com.androiddev.recruitmentsystem.Modules;

public class PostModule {
    String name;

    String phone;
    String subject;
    String location;
    String education;

    String time;
    String price;
    String id;
    String image;
    String currentTime, cregis, description;
    long applies, interviewing;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCregis() {
        return cregis;
    }

    public void setCregis(String cregis) {
        this.cregis = cregis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getApplies() {
        return applies;
    }

    public void setApplies(long applies) {
        this.applies = applies;
    }

    public long getInterviewing() {
        return interviewing;
    }

    public void setInterviewing(long interviewing) {
        this.interviewing = interviewing;
    }

    public PostModule(String name, String phone, String subject, String location, String education, String time, String price, String id, String image, String currentTime, String cregis, String description, long applies, long interviewing) {
        this.name = name;
        this.phone = phone;
        this.subject = subject;
        this.location = location;
        this.education = education;
        this.time = time;
        this.price = price;
        this.id = id;
        this.image = image;
        this.currentTime = currentTime;
        this.cregis = cregis;
        this.description = description;
        this.applies = applies;
        this.interviewing = interviewing;
    }

    public PostModule() {
    }
}
