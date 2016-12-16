package edu.uoc.library.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 6/12/16.
 */
public class Location {

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    /**
     * Method to get latitude of the monument characteristic location
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Method to set latitude of the monument characteristic location
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Method to get longitude of the monument characteristic location
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Method to set longitude of the monument characteristic location
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Method to return the geolocation, it return "Geo:" + latitude + "," + longitude
     * @return
     */
    public String getGeo(){
        return "Geo: " + getLatitude() + ", " + getLongitude();
    }
}
