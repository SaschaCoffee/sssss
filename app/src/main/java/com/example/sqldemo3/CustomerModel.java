package com.example.sqldemo3;

public class CustomerModel {
    private int id;
    private String name;
    private String phoneNumber;
    CustomerModel(String name, String phno) {
        this.name = name;
        this.phoneNumber = phno;
    }
    CustomerModel(int id, String name, String phno) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phno;
    }

    public CustomerModel(String name) {
        this.name = name;
    }

    int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    String getPhno() {
        return phoneNumber;
    }
    public void setPhno(String phno) {
        this.phoneNumber = phno;
    }
}

