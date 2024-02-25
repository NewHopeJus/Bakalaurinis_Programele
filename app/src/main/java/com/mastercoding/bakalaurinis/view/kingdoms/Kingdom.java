package com.mastercoding.bakalaurinis.view.kingdoms;

public class Kingdom {
    private String kingdomName;
    private int kingdomImg;

    public Kingdom(String kingdomName, int kingdomImg) {
        this.kingdomName = kingdomName;
        this.kingdomImg = kingdomImg;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }

    public int getKingdomImg() {
        return kingdomImg;
    }

    public void setKingdomImg(int kingdomImg) {
        this.kingdomImg = kingdomImg;
    }
}
