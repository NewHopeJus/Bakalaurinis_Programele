package com.mastercoding.bakalaurinis.dtos;

import java.util.List;

public class KingdomListsResponse {
    private List<KingdomDto> openedKingdoms;
    private List<KingdomDto> closedKingdoms;

    public List<KingdomDto> getOpenedKingdoms() {
        return openedKingdoms;
    }

    public List<KingdomDto> getClosedKingdoms() {
        return closedKingdoms;
    }

    public void setOpenedKingdoms(List<KingdomDto> openedKingdoms) {
        this.openedKingdoms = openedKingdoms;
    }

    public void setClosedKingdoms(List<KingdomDto> closedKingdoms) {
        this.closedKingdoms = closedKingdoms;
    }
}
