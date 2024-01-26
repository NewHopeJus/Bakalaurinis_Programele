package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuestionService {

    @GET("/api/questions/{id}")
    Call<Question> getQuestionById(@Path("id") long id);

}
