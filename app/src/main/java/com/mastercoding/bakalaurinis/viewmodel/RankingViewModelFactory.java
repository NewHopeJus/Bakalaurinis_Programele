package com.mastercoding.bakalaurinis.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class RankingViewModelFactory implements ViewModelProvider.Factory {
    private MineSecurityManager securityManager;

    public RankingViewModelFactory(MineSecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RankingViewModel(securityManager);
    }
}
