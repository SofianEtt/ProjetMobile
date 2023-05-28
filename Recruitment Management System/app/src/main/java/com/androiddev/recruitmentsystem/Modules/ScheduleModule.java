package com.androiddev.recruitmentsystem.Modules;

public class ScheduleModule {
    String tname;
    String tphone;
    String timage;
    String sname;
    String sphone;
    String slocation;
    String date;
    String time;
    String id;
    String cvImage;

    public String getCvImage() {
        return cvImage;
    }

    public void setCvImage(String cvImage) {
        this.cvImage = cvImage;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTimage() {
        return timage;
    }

    public void setTimage(String timage) {
        this.timage = timage;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSlocation() {
        return slocation;
    }

    public void setSlocation(String slocation) {
        this.slocation = slocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public ScheduleModule(String tname, String tphone, String timage, String sname, String sphone, String slocation, String date, String time, String id, String adid, String cvImage) {
        this.tname = tname;
        this.tphone = tphone;
        this.timage = timage;
        this.sname = sname;
        this.sphone = sphone;
        this.slocation = slocation;
        this.date = date;
        this.time = time;
        this.id = id;
        this.adid = adid;
        this.cvImage = cvImage;
    }

    String adid;


    public ScheduleModule() {
    }
}
