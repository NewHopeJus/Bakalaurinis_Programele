package com.mastercoding.bakalaurinis.model;

public class Kingdom {
    private String kingdomName;
    private String kingdomImg;

    public Kingdom(String kingdomName, String kingdomImg) {
        this.kingdomName = kingdomName;
        this.kingdomImg = kingdomImg;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }

    public String getKingdomImg() {
        return kingdomImg;
    }

    public void setKingdomImg(String kingdomImg) {
        this.kingdomImg = kingdomImg;
    }
}
