package com.example.mytaxxxx.data;

public class User {
    private String UID,Name,Phone;

    public User(String name, String phone, String UID) {
        Name = name;
        Phone = phone;
        this.UID = UID;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getUID() {
        return UID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
