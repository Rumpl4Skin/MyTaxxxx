package com.example.mytaxxxx.data;

public class Driver {
    private String carname,name,phone,uid;

    public Driver(String carname, String name, String phone, String uid) {
        this.carname = carname;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
    }

    public Driver() {
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
