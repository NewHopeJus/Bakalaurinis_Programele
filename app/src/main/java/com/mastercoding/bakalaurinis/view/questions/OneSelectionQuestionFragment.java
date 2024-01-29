package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;


public class OneSelectionQuestionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_selection_question, container, false);
        Bundle args = getArguments(); //kad gauti is activity klausimo informacija

        TextView descriptionTextView =  view.findViewById(R.id.textViewOneSelQuestionDescription);

        RadioButton radioButton1 = view.findViewById(R.id.radio_option1);
        RadioButton radioButton2 = view.findViewById(R.id.radio_option2);
        RadioButton radioButton3 = view.findViewById(R.id.radio_option3);
        RadioButton radioButton4 = view.findViewById(R.id.radio_option4);

        TextView coinsTextView = view.findViewById(R.id.textViewCoinsOneSelQuestion);
        TextView experienceTextView = view.findViewById(R.id.textViewExperienceOneSelQuestion);


        if(args!=null){
            String description = args.getString("description");
            descriptionTextView.setText(description);

            String option1 = args.getString("option1");
            radioButton1.setText(option1);

            String option2 = args.getString("option2");
            radioButton2.setText(option2);

            String option3 = args.getString("option3");
            radioButton3.setText(option3);

            String option4 = args.getString("option4");
            radioButton4.setText(option4);

            String coins = args.getString("coins");
            coinsTextView.setText(coins);

            String experience = args.getString("experience");
            experienceTextView.setText(experience);

        }
        return view;
    }





}