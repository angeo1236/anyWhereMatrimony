package com.techxform.anywherematrimony.data;

public class NavListModel {
    private String navName;
    private int imageName;

    public NavListModel(String navName, int imageName) {
        this.navName = navName;
        this.imageName = imageName;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }
}
