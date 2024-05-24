package com.mastercoding.bakalaurinis.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class QuestionViewModelFactory implements ViewModelProvider.Factory {
    private String topicName;
    private String levelName;
    private MineSecurityManager securityManager;

    public QuestionViewModelFactory(String levelName, String topicName, MineSecurityManager securityManager) {
        this.topicName = topicName;
        this.levelName = levelName;
        this.securityManager = securityManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuestionViewModel(levelName, topicName, securityManager);
    }
}
