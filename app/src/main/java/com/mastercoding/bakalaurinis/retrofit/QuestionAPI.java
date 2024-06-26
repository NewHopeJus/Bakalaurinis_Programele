package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionAPI {

    @GET("/api/questions/{id}")
    Call<Question> getQuestionById(@Path("id") long id);

    @GET("/api/questions/{level}/{topic}")
    Call<Question> getQuestionByLevelAndTopic(@Path("level") String level, @Path("topic") String topic,
                                              @Header("Authorization") String jwtToken);

    @POST("api/questions/answerSubmit")
    Call<AnswerSubmitResponse> submitAnswer(@Body AnswerSubmitRequest answerSubmitRequest,
                                            @Header("Authorization") String jwtToken);

}
