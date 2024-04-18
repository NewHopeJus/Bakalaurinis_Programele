package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.StatisticsResponse;
import com.mastercoding.bakalaurinis.repository.StatisticsRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class StatisticsViewModel extends ViewModel {
    private MutableLiveData<StatisticsResponse> statisticsResponseMutableLiveData ;
    private StatisticsRepository statisticsRepository;

    public StatisticsViewModel(MineSecurityManager securityManager) {
        this.statisticsRepository = new StatisticsRepository(securityManager);
        this.statisticsResponseMutableLiveData = statisticsRepository.getStatisticsMutableLiveData();
    }

    public void getStatistics(){
        statisticsResponseMutableLiveData = statisticsRepository.getStatistics();
    }

    public MutableLiveData<StatisticsResponse> getStatisticsResponseMutableLiveData() {
        return statisticsResponseMutableLiveData;
    }
}
