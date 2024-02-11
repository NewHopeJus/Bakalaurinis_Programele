package com.mastercoding.bakalaurinis.view.questions;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionService;
import com.mastercoding.bakalaurinis.retrofit.RetrofitClientInstance;
import com.mastercoding.bakalaurinis.security.SecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionFetchingService {

    private final Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    private final QuestionService questionService = retrofit.create(QuestionService.class);
    private SecurityManager securityManager;

    private QuestionFetchingListener listener;

    public void setListener(QuestionFetchingListener listener) {
        this.listener = listener; //i listener vieta ateina question activity
    }

    public void getQuestion(String levelName, String topicName, Context context) {

        securityManager = new SecurityManager(context);

        Call<Question> call = questionService.getQuestionByLevelAndTopic(levelName, topicName, securityManager.getToken());

        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response) {
                if (response.isSuccessful()) {
                    Question question = response.body();
                    listener.onQuestionAvailable(question);


                } else {
                    Log.e("Question Fetching Service", "Getting question from backend failed");
                    Toast.makeText(context, "Klaida. Nepavyko gauti klausimo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("Question Fetching Service", "Getting question from backend failed", t);
                Toast.makeText(context, "Klaida. Nepavyko gauti klausimo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static Fragment loadQuestionFragment(Question question, Context context) {
        Bundle args = new Bundle();
        Fragment fragment = new Fragment();

        args.putParcelable("questionObject", question);

        //Pridedam fragmenta su klausimu
        if (question != null) {

            switch (question.getQuestionType()) {
                case "ONE_ANSWER":
                    fragment = new OneSelectionQuestionFragment();
                    break;
                case "MULTIPLE_ANSWER":
                    fragment = new MultipleChoiceQuestionFragment();
                    break;
                default:
                    fragment = new OpenQuestionFragment();
                    break;

            }
        }
        fragment.setArguments(args); //setArgs nes naudojam ta bundle nepamirsti
        return fragment;
    }


}
