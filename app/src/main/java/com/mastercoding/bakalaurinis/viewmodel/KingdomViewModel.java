package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.repository.KingdomRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class KingdomViewModel extends ViewModel {
    private MutableLiveData<KingdomListsResponse> kingdomListsResponseLiveData;
    private KingdomRepository kingdomRepository;

    public KingdomViewModel(MineSecurityManager securityManager) {
        this.kingdomRepository = new KingdomRepository(securityManager);
    }

    public void getKingdoms() {
        this.kingdomListsResponseLiveData = kingdomRepository.getKingdoms();
    }

    public LiveData<KingdomListsResponse> getKingdomListsResponseLiveData() {
        return kingdomListsResponseLiveData;
    }
}
