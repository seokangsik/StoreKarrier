package com.skgroup4.android.storekarrier;

import java.io.Serializable;

/**
 * Created by Seo on 2017-08-09.
 */

public class RepoHouse implements Serializable{
    private String latitude;
    private String avg;
    private String id;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
