package com.mastercoding.bakalaurinis.view.questions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentCorrectAnswerBinding;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.SecurityManager;
import com.mastercoding.bakalaurinis.view.main.RegisterActivity;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;

import java.util.Objects;


public class CorrectAnswerFragment extends Fragment {
    private FragmentCorrectAnswerBinding binding;
    private QuestionViewModel questionViewModel;
    private String levelName;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_correct_answer, container, false);


        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");

        SecurityManager securityManager = new SecurityManager(requireContext());

        questionViewModel = new ViewModelProvider(getActivity(), new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewExperiencePoints = binding.textViewExpPointsCorrectAnswer;
        TextView textViewCoins = binding.textViewCoinsCorrectAnswer;
        TextView textViewDescription = binding.textViewQuestionDescriptionCorrectAnswer;
        TextView textViewCorrectAnswer = binding.textViewCorrectAnswer;

        Bundle args = getArguments();

        if (args != null) {
            Question question = args.getParcelable("questionObject");
            if (question != null) {
                textViewExperiencePoints.setText(question.getExperience().toString());
                textViewCoins.setText(question.getCoins().toString());
                textViewDescription.setText(question.getDescription());
                textViewCorrectAnswer.setText(args.getString("correctAnswerText"));
            }
        }


        Button buttonContinue = binding.buttonContinueCorrectAnswerFragment;
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    questionViewModel.getQuestion();


            }
        });
    }

}