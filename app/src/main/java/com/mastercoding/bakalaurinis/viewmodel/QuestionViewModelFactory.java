package com.mastercoding.bakalaurinis.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.bakalaurinis.security.SecurityManager;

public class QuestionViewModelFactory implements ViewModelProvider.Factory {
    private String topicName;
    private String levelName;
    private SecurityManager securityManager;

    public QuestionViewModelFactory(String levelName, String topicName,  SecurityManager securityManager) {
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
