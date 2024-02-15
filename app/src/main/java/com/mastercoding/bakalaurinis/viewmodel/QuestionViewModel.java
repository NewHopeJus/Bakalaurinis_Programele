package com.mastercoding.bakalaurinis.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.repository.QuestionRepository;
import com.mastercoding.bakalaurinis.security.SecurityManager;

/**Kreipiasi i Question Repository klausimo gavimui
 * Reikia ne tiesiog view model o ir factory nes noriu dar perduoti ir parametrus
 */

public class QuestionViewModel extends ViewModel{
    private QuestionRepository questionRepository;
    private MutableLiveData<Question> questionLiveData;

    private String level;
    private String topic;

    public QuestionViewModel(String level, String topic, SecurityManager securityManager) {
        questionRepository = new QuestionRepository(securityManager);
        this.level = level;
        this.topic = topic;
        //questionLiveData.setValue(questionRepository.getQuestion(level, topic).getValue());
       // questionLiveData = new MutableLiveData<>();
       // questionLiveData = questionRepository.getQuestion(level, topic);

    }

    public void getQuestion(){
        questionLiveData = questionRepository.getQuestion(level, topic);
    }
    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

    public void resetLiveData(){
        questionLiveData = null;
    }

}
