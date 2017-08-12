package com.skgroup4.android.storekarrier.item;

import java.io.Serializable;

/**
 * Created by Seo on 2017-08-09.
 */

public class RepoHouse implements Serializable{
    private String hostName;
    private String hostImg;
    private String hostTel;
    private String houseImg;
    private String latitude;
    private String longitude;
    private String avg;
    private String price;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostImg() {
        return hostImg;
    }

    public void setHostImg(String hostImg) {
        this.hostImg = hostImg;
    }

    public String getHostTel() {
        return hostTel;
    }

    public void setHostTel(String hostTel) {
        this.hostTel = hostTel;
    }

    public String getHouseImg() {
        return houseImg;
    }

    public void setHouseImg(String houseImg) {
        this.houseImg = houseImg;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
