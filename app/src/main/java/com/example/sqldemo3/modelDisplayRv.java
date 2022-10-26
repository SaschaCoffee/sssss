package com.example.sqldemo3;

public class modelDisplayRv {
    private int id;
    private String name;
    private String phoneNumber;
    private String squat,bench,deadlift;

    public modelDisplayRv(int id, String name, String phoneNumber, String squat, String bench, String deadlift) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.squat = squat;
        this.bench = bench;
        this.deadlift = deadlift;
    }

    public int getId() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSquat() {
        return squat;
    }

    public void setSquat(String squat) {
        this.squat = squat;
    }

    public String getBench() {
        return bench;
    }

    public void setBench(String bench) {
        this.bench = bench;
    }

    public String getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(String deadlift) {
        this.deadlift = deadlift;
    }
}
