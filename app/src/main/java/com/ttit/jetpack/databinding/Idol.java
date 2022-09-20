package com.ttit.jetpack.databinding;

public class Idol {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private Integer localImage;
    private String networkImage;

    public Integer getLocalImage() {
        return localImage;
    }

    public void setLocalImage(Integer localImage) {
        this.localImage = localImage;
    }

    public String getNetworkImage() {
        return networkImage;
    }

    public void setNetworkImage(String networkImage) {
        this.networkImage = networkImage;
    }
}
