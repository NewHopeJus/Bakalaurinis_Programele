package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.RankingDto;
import com.mastercoding.bakalaurinis.repository.RankingRepository;
import com.mastercoding.bakalaurinis.repository.ShopItemRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import java.util.List;

public class RankingViewModel extends ViewModel {
    private RankingRepository rankingRepository;
    private MutableLiveData<List<RankingDto>> rankings;

    public RankingViewModel(MineSecurityManager securityManager) {
        this.rankingRepository = new RankingRepository(securityManager);
        this.rankings = rankingRepository.getRankings();
    }

    public void getRankingList() {
        rankings = rankingRepository.getRankingList();
    }

    public MutableLiveData<List<RankingDto>> getRankings() {
        return rankings;
    }
}
