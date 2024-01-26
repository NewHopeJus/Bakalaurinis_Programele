package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.model.Question;


public class OneSelectionQuestionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_selection_question, container, false);
        Bundle args = getArguments(); //kad gauti is activity klausimo informacija
        TextView descriptionTextView =  (TextView)view.findViewById(R.id.textViewOneSelQuestionDescription);

        if(args!=null){
            String description = args.getString("description");
            descriptionTextView.setText(description);

        }
        return view;
    }





}