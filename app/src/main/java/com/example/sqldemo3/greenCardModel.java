package com.example.sqldemo3;

public class greenCardModel {
    public greenCardModel(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    private int Thumbnail;
}
