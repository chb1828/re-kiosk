package org.rtruesoft.kiosk.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class GetResultDetails {

    @SerializedName("formatted_address")
    private String address;

    private String name;

    private ArrayList photos;

    private String placeId;

    private double rating;

    private Map<String,Object> geometry;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Map<String, Object> getGeometry() {
        return geometry;
    }

    public void setGeometry(Map<String, Object> geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "GetResultDetails{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", photos=" + photos +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", geometry=" + geometry +
                '}';
    }
}
