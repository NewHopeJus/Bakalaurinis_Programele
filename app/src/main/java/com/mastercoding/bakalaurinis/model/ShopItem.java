package com.mastercoding.bakalaurinis.model;

public class ShopItem {
    private Long id;
    private String name;
    private String imgViewId;
    private String imgName;
    private Integer price;

    public ShopItem(Long id, String name, String imgViewId, String imgName, Integer price) {
        this.id = id;
        this.name = name;
        this.imgViewId = imgViewId;
        this.imgName = imgName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgViewId() {
        return imgViewId;
    }

    public void setImgViewId(String imgViewId) {
        this.imgViewId = imgViewId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
