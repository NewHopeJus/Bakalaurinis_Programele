package com.mastercoding.bakalaurinis.repository;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionRepository {
    private QuestionAPI questionAPI;
    private MineSecurityManager securityManager;
    private MutableLiveData<Question> questionMutableLiveData;

    public QuestionRepository(MineSecurityManager securityManager) {
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
                    questionMutableLiveData.setValue(null);                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("Question Fetching Service", "Getting question from backend failed", t);
            }
        });

        return questionMutableLiveData;
    }

    public MutableLiveData<AnswerSubmitResponse> getAnswerSubmitResponse(AnswerSubmitRequest answerSubmitRequest) {
        MutableLiveData<AnswerSubmitResponse> answerSubmitResponseLiveData = new MutableLiveData<>();
        Call<AnswerSubmitResponse> call = questionAPI.submitAnswer(answerSubmitRequest, securityManager.getToken());
        call.enqueue(new Callback<AnswerSubmitResponse>() {
            @Override
            public void onResponse(@NonNull Call<AnswerSubmitResponse> call, @NonNull Response<AnswerSubmitResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        answerSubmitResponseLiveData.setValue(response.body());
                    }

                } else {
                    Log.e("Question Fetching Service", "Sending the submission failed");
                }
            }

            @Override
            public void onFailure(Call<AnswerSubmitResponse> call, Throwable t) {
                Log.e("Question Fetching Service", "Sending the submission failed", t);
            }
        });

        return answerSubmitResponseLiveData;
    }

}
