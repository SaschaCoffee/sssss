package com.example.sqldemo3;

public class CustomerModel {
    private int id;
    private String name;
    private String openpl;
    private String insta;
    private String bundes;
    private String verband;
    private int datum;
    private String ort;
    private String geschlecht;
    private int alter;
    private int squat;
    private int bench;
    private int deadlift;

    public CustomerModel(int id, String name, String openpl, String insta, String bundes, String verband, int datum, String ort, String geschlecht, int alter, int squat, int bench, int deadlift) {
        this.id = id;
        this.name = name;
        this.openpl = openpl;
        this.insta = insta;
        this.bundes = bundes;
        this.verband = verband;
        this.datum = datum;
        this.ort = ort;
        this.geschlecht = geschlecht;
        this.alter = alter;
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

    public String getOpenpl() {
        return openpl;
    }

    public void setOpenpl(String openpl) {
        this.openpl = openpl;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getBundes() {
        return bundes;
    }

    public void setBundes(String bundes) {
        this.bundes = bundes;
    }

    public String getVerband() {
        return verband;
    }

    public void setVerband(String verband) {
        this.verband = verband;
    }

    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public int getSquat() {
        return squat;
    }

    public void setSquat(int squat) {
        this.squat = squat;
    }

    public int getBench() {
        return bench;
    }

    public void setBench(int bench) {
        this.bench = bench;
    }

    public int getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(int deadlift) {
        this.deadlift = deadlift;
    }
}