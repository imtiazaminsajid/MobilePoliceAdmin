package com.example.ambit.mobilepoliceadmin.Model;

import com.google.firebase.database.Exclude;

public class AllDetails {

    private String timeAndDate;
    private String locationName;
    private String complain;
    private String imageUrl;
    private String phoneNumber;
    private String crimeType;
    private Double lat;
    private Double lon;

    private String key;

    public AllDetails() {
    }

    public AllDetails(String timeAndDate, String locationName, String complain, String imageUrl, String phoneNumber, String crimeType, Double lat, Double lon) {
        this.timeAndDate = timeAndDate;
        this.locationName = locationName;
        this.complain = complain;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.crimeType = crimeType;
        this.lat = lat;
        this.lon = lon;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
