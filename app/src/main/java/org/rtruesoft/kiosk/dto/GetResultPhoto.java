package org.rtruesoft.kiosk.dto;

import com.google.gson.annotations.SerializedName;


public class GetResultPhoto {
    private int height;
    @SerializedName("html_attributions")
    private Object htmlAttributions;
    private String photo_reference;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Object getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(Object htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "GetResultPhoto{" +
                "height=" + height +
                ", htmlAttributions=" + htmlAttributions +
                ", photo_reference='" + photo_reference + '\'' +
                ", width=" + width +
                '}';
    }
}
