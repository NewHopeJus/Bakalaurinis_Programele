package com.mastercoding.bakalaurinis.view.questions;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentCorrectAnswerBinding;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;


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

        MineSecurityManager securityManager = new MineSecurityManager(requireContext());


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

                if (question.getQuestionTopic().equals("Trupmenos")) {
                    textViewDescription.setText(Html.fromHtml(question.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    textViewDescription.setText(question.getDescription());
                }

                textViewCorrectAnswer.setText(args.getString("correctAnswerText"));
            }
            Boolean kingdomOpened = args.getBoolean("kingdomOpened");
            String textToShow = args.getString("openedKingdomText");
            if(kingdomOpened){
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View customLayout = getLayoutInflater().inflate(R.layout.opening_kingdom_dialog_custom_layout, null);
                TextView textToDisplayTextView = customLayout.findViewById(R.id.textViewOpeningKingdom);
                Button btn = customLayout.findViewById(R.id.buttonTestiMonsterKilled);

                builder.setView(customLayout);

                textToDisplayTextView.setText(textToShow);
                AlertDialog dialog = builder.create();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }


        }


        Button buttonContinue = binding.buttonContinueCorrectAnswerFragment;
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               questionViewModel.setAnswered(false);
                    questionViewModel.getQuestion();
            }
        });
    }


}