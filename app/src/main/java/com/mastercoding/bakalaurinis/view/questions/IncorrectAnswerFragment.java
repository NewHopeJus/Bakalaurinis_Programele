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
import com.mastercoding.bakalaurinis.databinding.FragmentCorrectAnswerBinding;
import com.mastercoding.bakalaurinis.databinding.FragmentIncorrectAnswerBinding;
import com.mastercoding.bakalaurinis.model.Question;


public class IncorrectAnswerFragment extends Fragment {

    private FragmentIncorrectAnswerBinding fragmentIncorrectAnswerBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentIncorrectAnswerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_incorrect_answer, container, false);

        return fragmentIncorrectAnswerBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewExperiencePoints = fragmentIncorrectAnswerBinding.textViewExpPointsInCorrectAnswer;
        TextView textViewDescription = fragmentIncorrectAnswerBinding.textViewQuestionDescriptionInCorrectAnswerFragment;
        TextView textViewCorrectAnswer = fragmentIncorrectAnswerBinding.textViewCorrectAnswer;

        Bundle args = getArguments();

        if (args != null) {
            Question question = args.getParcelable("questionObject");
            if (question != null) {
                String experiencePoints = "+" + question.getExperience().toString();
                textViewExperiencePoints.setText(experiencePoints);
                textViewDescription.setText(question.getDescription());
                textViewCorrectAnswer.setText(args.getString("correctAnswerText"));
            }
        }

    }

}