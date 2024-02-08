package com.mastercoding.bakalaurinis.view.questions;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.model.Option;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionService;
import com.mastercoding.bakalaurinis.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OneSelectionQuestionFragment extends Fragment {

    private Question question;
    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    QuestionService questionService = retrofit.create(QuestionService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one_selection_question, container, false);

        Bundle args = getArguments(); //kad gauti is activity klausimo informacija

        TextView descriptionTextView = view.findViewById(R.id.textViewOneSelQuestionDescription);

        RadioButton radioButton1 = view.findViewById(R.id.radio_option1);
        RadioButton radioButton2 = view.findViewById(R.id.radio_option2);
        RadioButton radioButton3 = view.findViewById(R.id.radio_option3);
        RadioButton radioButton4 = view.findViewById(R.id.radio_option4);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroupOneSelQuestion);

        TextView coinsTextView = view.findViewById(R.id.textViewCoinsOneSelQuestion);
        TextView experienceTextView = view.findViewById(R.id.textViewExperienceOneSelQuestion);

        Button buttonSubmit = view.findViewById(R.id.buttonSubmitOneSelQuestion);

        if (args != null) {
            question = args.getParcelable("questionObject");

            if (question != null) {
                List<Option> optionList = question.getOptions();
                descriptionTextView.setText(question.getDescription());

                radioButton1.setText(optionList.get(0).getText());
                radioButton1.setTag(optionList.get(0).getId());

                radioButton2.setText(optionList.get(1).getText());
                radioButton2.setTag(optionList.get(1).getId());


                radioButton3.setText(optionList.get(2).getText());
                radioButton3.setTag(optionList.get(2).getId());

                radioButton4.setText(optionList.get(3).getText());
                radioButton4.setTag(optionList.get(3).getId());


                String coins = question.getCoins().toString();
                coinsTextView.setText(coins);

                String experience = question.getExperience().toString();
                experienceTextView.setText(experience);
            }

        }


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioGroup != null) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selected = radioGroup.findViewById(selectedId);
                    AnswerSubmitRequest answerSubmitRequest = new AnswerSubmitRequest(question.getId(),
                            (String) selected.getText(), (Long) selected.getTag());
//                    Toast.makeText(getContext(), selected.getText(), Toast.LENGTH_SHORT).show();


                    //ieskom koki pasirinko


                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                    String token = sharedPreferences.getString("jwt_token", "");
                    Call<AnswerSubmitResponse> call = questionService.submitAnswer(answerSubmitRequest, "Bearer " + token);
                    call.enqueue(new Callback<AnswerSubmitResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<AnswerSubmitResponse> call, @NonNull Response<AnswerSubmitResponse> response) {
                            if (response.isSuccessful()) {
                                //Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();

                                if (response.body() != null) {
                                  //  AnswerSubmitResponse answerSubmitResponse = response.body();
                                    if (response.body().getAnswerCorrect()) {
                                        // Toast.makeText(getContext(), "Good", Toast.LENGTH_SHORT).show();
                                        if (getActivity() != null) {
                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                            CorrectAnswerFragment correctAnswerFragment = new CorrectAnswerFragment();
                                            fragmentManager.beginTransaction()
                                                    .replace(R.id.fragment_container_question_fragment, correctAnswerFragment)
                                                    .commit();
                                        }
                                    }
                                    else {
                                        // Toast.makeText(getContext(), "Not good", Toast.LENGTH_SHORT).show();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                        if (getActivity() != null) {
                                            IncorrectAnswerFragment incorrectAnswerFragment = new IncorrectAnswerFragment();
                                            fragmentManager.beginTransaction()
                                                    .replace(R.id.fragment_container_question_fragment, incorrectAnswerFragment)
                                                    .commit();
                                        }

                                    }

                                }

                            } else {
                                Log.e("One Selection Fragment", "Sending the submission failed");
                                Toast.makeText(getContext(), "Klaida. Nepavyko išsiųsti atsakymo.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AnswerSubmitResponse> call, Throwable t) {
                            Log.e("One Selection Fragment", "Sending the submission failed", t);
                            Toast.makeText(getContext(), "Klaida. Nepavyko išsiųsti atsakymo.", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

        return view;


    }

}