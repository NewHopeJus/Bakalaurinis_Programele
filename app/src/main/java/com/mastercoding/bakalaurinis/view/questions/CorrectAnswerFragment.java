package com.mastercoding.bakalaurinis.view.questions;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
        ImageView greenCutie = binding.greenCutieImageCorrectAnswerFragment;

        Bundle args = getArguments();

        if (args != null) {
            Question question = args.getParcelable("questionObject");
            if (question != null) {
                String experiencePoints = "+" + question.getExperience().toString();
                textViewExperiencePoints.setText(experiencePoints);

                String coins = "+" + question.getCoins().toString();
                textViewCoins.setText(coins);

                if (question.getQuestionTopic().equals("Trupmenos")) {
                    textViewDescription.setText(Html.fromHtml(question.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    textViewDescription.setText(question.getDescription());
                }

                textViewCorrectAnswer.setText(args.getString("correctAnswerText"));
            }

            TextView textViewTeisingai = binding.textViewCorrectFragmentHeader;
            YoYo.with(Techniques.DropOut)
                    .duration(700)
                    .playOn(textViewTeisingai);



            YoYo.with(Techniques.ZoomIn)
                    .duration(700)
                    .repeat(2)
                    .playOn(textViewExperiencePoints);


            YoYo.with(Techniques.ZoomIn)
                    .duration(700)
                    .repeat(2)
                    .playOn(textViewCoins);


            Animation jumpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.jump);
            greenCutie.startAnimation(jumpAnimation);


            Boolean kingdomOpened = args.getBoolean("kingdomOpened");
            String textToShow = args.getString("openedKingdomText");
            if(kingdomOpened){
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View customLayout = getLayoutInflater().inflate(R.layout.opening_kingdom_dialog_custom_layout, null);
                TextView textToDisplayTextView = customLayout.findViewById(R.id.textViewOpeningKingdom);
                Button btn = customLayout.findViewById(R.id.buttonTestiMonsterKilled);

                ImageView imageViewKilled =  customLayout.findViewById(R.id.imageViewMonsterKilled);
                AnimationDrawable monsterKill = (AnimationDrawable) imageViewKilled.getBackground();
                monsterKill.start();
                builder.setView(customLayout);


                ImageView imageViewFirework1 =  customLayout.findViewById(R.id.imageViewFirework1);
                ImageView imageViewFirework2 =  customLayout.findViewById(R.id.imageViewFirework2);

                YoYo.with(Techniques.Flash)
                        .duration(700)
                        .repeat(5)
                        .playOn(imageViewFirework1);

                YoYo.with(Techniques.Flash)
                        .duration(700)
                        .repeat(5)
                        .playOn(imageViewFirework2);



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