package org.rtruesoft.kiosk.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetResult {


    @SerializedName("html_attributions")
    private List htmlAttr;

    @SerializedName("next_page_token")
    private String token;

    @SerializedName("results")
    private ArrayList<GetResultDetails> results;

    private String status;

    public List getHtmlAttr() {
        return htmlAttr;
    }

    public void setHtmlAttr(List htmlAttr) {
        this.htmlAttr = htmlAttr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<GetResultDetails> getResults() {
        return results;
    }

    public void setResults(ArrayList<GetResultDetails> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetResult{" +
                "htmlAttr=" + htmlAttr +
                ", token='" + token + '\'' +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }

    /*      @SerializedName("formatted_address")
    private String address;

    private String name;

    private Map<String,String> photos;

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

    public Map<String, String> getPhotos() {
        return photos;
    }

    public void setPhotos(Map<String, String> photos) {
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
        return "GetResult{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", photos=" + photos +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", geometry=" + geometry +
                '}';
    }*/
}
