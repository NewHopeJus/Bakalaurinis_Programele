package com.mastercoding.bakalaurinis.repository;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.SecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionRepository {
    private QuestionAPI questionAPI;
    private SecurityManager securityManager;
    private MutableLiveData<Question> questionMutableLiveData;

    public QuestionRepository(SecurityManager securityManager) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        questionAPI = retrofit.create(QuestionAPI.class);
        this.securityManager = securityManager;
        questionMutableLiveData = new MutableLiveData<>();
    }

    //returnina live data questiona

    public MutableLiveData<Question> getQuestion(String levelName, String topicName) {

        Call<Question> call = questionAPI.getQuestionByLevelAndTopic(levelName, topicName, securityManager.getToken());

        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response) {
                Log.e("Response body", response.toString());

                if (response.isSuccessful()) {
                    questionMutableLiveData.setValue(response.body());

                } else {
                    Log.e("Question Fetching Service", "Getting question from backend failed 1");
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("Question Fetching Service", "Getting question from backend failed 2", t);
            }
        });

        return questionMutableLiveData;
    }

}
