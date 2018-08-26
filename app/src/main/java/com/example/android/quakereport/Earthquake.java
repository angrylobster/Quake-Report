package com.example.android.quakereport;

public class Earthquake {
    private String mLocation;
    private String mMagnitude;
    private String mDate;

    public Earthquake(String magnitude, String location,  String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getDate() {
        return mDate;
    }
}
