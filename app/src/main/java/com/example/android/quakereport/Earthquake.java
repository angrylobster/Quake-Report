package com.example.android.quakereport;

public class Earthquake {
    private String mLocation;
    private double mMagnitude;
    private long mUnixEpochTime;
    private String mUrl;

    public Earthquake(double magnitude, String location,  long unixEpochTime, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mUnixEpochTime = unixEpochTime;
        mUrl = url;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getUnixEpochTime(){
        return mUnixEpochTime;
    }

    public String getUrl() {
        return mUrl;
    }
}
