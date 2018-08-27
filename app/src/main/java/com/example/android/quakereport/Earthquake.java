package com.example.android.quakereport;

public class Earthquake {
    private String mLocation;
    private String mMagnitude;
    private long mUnixEpochTime;

    public Earthquake(String magnitude, String location,  long unixEpochTime) {
        mMagnitude = magnitude;
        mLocation = location;
        mUnixEpochTime = unixEpochTime;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public long getUnixEpochTime(){
        return mUnixEpochTime;
    }
}
