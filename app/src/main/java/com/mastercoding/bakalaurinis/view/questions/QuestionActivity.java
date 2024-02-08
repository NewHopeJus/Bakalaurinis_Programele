package com.mastercoding.bakalaurinis.view.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Option;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionService;
import com.mastercoding.bakalaurinis.retrofit.RetrofitClientInstance;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionActivity extends AppCompatActivity {

    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    QuestionService questionService = retrofit.create(QuestionService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        String levelName = getIntent().getStringExtra("levelName");
        String topicName = getIntent().getStringExtra("topicName");



        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String token = sharedPreferences.getString("jwt_token", "");
        Call<Question> call = questionService.getQuestionByLevelAndTopic(levelName, topicName, "Bearer " + token);

        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response.isSuccessful()) {
                    Question question = response.body();

                    //perduodam i fragmenta visus duomenis apie klausima
                    Bundle args = new Bundle();
//                    args.putString("description", question.getDescription());
//
//
//                    args.putString("coins", String.valueOf(question.getCoins()));
//                    args.putString("experience", String.valueOf(question.getExperience()));


                    args.putParcelable("questionObject", (Parcelable) question);

                    //Pridedam fragmenta su klausimu
                    if (savedInstanceState == null) { //This check is used to determine whether the activity is
                        // being started for the first time or being recreated after a configuration change.


                        ///!!!! Pakeisti sita dubliikacija kodo
                        if (question.getQuestionType().equals("ONE_ANSWER")) {
//                            args.putString("option1", options.get(0).getText());
//                            args.putString("option2", options.get(1).getText());
//                            args.putString("option3", options.get(2).getText());
//                            args.putString("option4", options.get(3).getText());


                            OneSelectionQuestionFragment oneSelectionQuestionFragment = new OneSelectionQuestionFragment();
                            oneSelectionQuestionFragment.setArguments(args); //setArgs nes naudojam ta bundle nepamirsti
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container_question_fragment, oneSelectionQuestionFragment)
                                    .commit();
                        } else if (question.getQuestionType().equals("MULTIPLE_ANSWER")) {
//                            args.putString("option1", options.get(0).getText());
//                            args.putString("option2", options.get(1).getText());
//                            args.putString("option3", options.get(2).getText());
//                            args.putString("option4", options.get(3).getText());

                            MultipleChoiceQuestionFragment multipleChoiceQuestionFragment = new MultipleChoiceQuestionFragment();
                            multipleChoiceQuestionFragment.setArguments(args); //setArgs nes naudojam ta bundle nepamirsti
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container_question_fragment, multipleChoiceQuestionFragment)
                                    .commit();
                        }
                        //atviras klausimas
                        else if (question.getQuestionType().equals("OPEN_ANSWER")) {
                            OpenQuestionFragment openQuestionFragment = new OpenQuestionFragment();
                            openQuestionFragment.setArguments(args); //setArgs nes naudojam ta bundle nepamirsti
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container_question_fragment, openQuestionFragment)
                                    .commit();
                        }
                    }

                }
                else {
                    Log.e("Question Activity", "Getting question from backend failed");
                    Toast.makeText(QuestionActivity.this, "Klaida. Nepavyko gauti klausimo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("Question Activity", "Getting question from backend failed", t);
                Toast.makeText(QuestionActivity.this, "Klaida. Nepavyko gauti klausimo.", Toast.LENGTH_SHORT).show();
            }

        });


        Toolbar toolbar = findViewById(R.id.toolbarQuestionActivity);
        setSupportActionBar(toolbar);

        //kad paslepti teksta toolbaro
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbaro menu pridedam
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // atgal mygtukas ant toolbaro
        //Griztam i lygio temu langa
        if (item.getItemId() == R.id.action_back) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_home) {
            //Griztam i pagrindini puslapi
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("home", 0);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

