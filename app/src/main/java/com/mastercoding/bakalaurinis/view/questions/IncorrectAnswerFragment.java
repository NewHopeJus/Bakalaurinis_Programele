package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentIncorrectAnswerBinding;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.SecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;


public class IncorrectAnswerFragment extends Fragment {

    private FragmentIncorrectAnswerBinding fragmentIncorrectAnswerBinding;
    private QuestionViewModel questionViewModel;
    private String levelName;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentIncorrectAnswerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_incorrect_answer, container, false);

        SecurityManager securityManager = new SecurityManager(requireContext());

        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");
        questionViewModel = new ViewModelProvider(getActivity(), new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);
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

        Button buttonContinue = fragmentIncorrectAnswerBinding.buttonContinueInCorrectAnswerFragment;
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionViewModel.getQuestion();
            }
        });

    }

}