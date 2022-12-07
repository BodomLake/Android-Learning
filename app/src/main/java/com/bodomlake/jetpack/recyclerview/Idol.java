package com.bodomlake.jetpack.recyclerview;

public class Idol {

    private String image;
    private String chName;
    private String enName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Idol(String image, String chName, String enName) {
        this.image = image;
        this.chName = chName;
        this.enName = enName;
    }
}
