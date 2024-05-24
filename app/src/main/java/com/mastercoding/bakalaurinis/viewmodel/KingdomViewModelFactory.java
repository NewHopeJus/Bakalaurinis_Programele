package com.mastercoding.bakalaurinis.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class KingdomViewModelFactory implements ViewModelProvider.Factory {
    private MineSecurityManager securityManager;

    public KingdomViewModelFactory(MineSecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new KingdomViewModel(securityManager);
    }
}
