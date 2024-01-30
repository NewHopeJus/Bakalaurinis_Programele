package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;


public class OpenQuestionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_open_question, container, false);
        Bundle args = getArguments(); //kad gauti is activity klausimo informacija

        TextView descriptionTextView =  view.findViewById(R.id.textViewOpenQuestionDescription);
       if(args!=null){
           String description = args.getString("description");
           descriptionTextView.setText(description);

           if (description != null && description.length() > 50) {
               descriptionTextView.setTextSize(16);
           }

       }

        return view;
    }
}