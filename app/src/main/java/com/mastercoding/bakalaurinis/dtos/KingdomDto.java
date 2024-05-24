package com.mastercoding.bakalaurinis.dtos;
public class KingdomDto {
    private Long id;
    private String name;
    private String img;
    private Boolean isOpened;

    public KingdomDto(Long id, String name, String img, Boolean isOpened) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.isOpened = isOpened;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
