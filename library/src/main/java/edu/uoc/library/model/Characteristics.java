package edu.uoc.library.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 6/12/16.
 */
public class Characteristics {

    @SerializedName("year")
    private String year;

    @SerializedName("location")
    private Location location;

    /**
     * Method to get year of the monument characteristics
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * Method to set year of the monument characteristics
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Method to get location of the monument characteristics
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Method to set location of the monument characteristics
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
