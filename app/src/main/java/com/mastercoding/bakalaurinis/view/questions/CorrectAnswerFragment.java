package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityMainBinding;
import com.mastercoding.bakalaurinis.databinding.FragmentCorrectAnswerBinding;
import com.mastercoding.bakalaurinis.model.Question;


public class CorrectAnswerFragment extends Fragment {
    private FragmentCorrectAnswerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_correct_answer, container, false);

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

    }
}