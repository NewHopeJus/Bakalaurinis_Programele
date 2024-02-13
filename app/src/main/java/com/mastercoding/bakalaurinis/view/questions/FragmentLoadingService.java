package com.mastercoding.bakalaurinis.view.questions;

import android.content.Context;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.mastercoding.bakalaurinis.model.Question;

public class FragmentLoadingService {

    public static Fragment loadQuestionFragment(Question question) {
        Bundle args = new Bundle();
        Fragment fragment = new Fragment();

        args.putParcelable("questionObject", question);

        //Pridedam fragmenta su klausimu
        if (question != null) {

            switch (question.getQuestionType()) {
                case "ONE_ANSWER":
                    fragment = new OneSelectionQuestionFragment();
                    break;
                case "MULTIPLE_ANSWER":
                    fragment = new MultipleChoiceQuestionFragment();
                    break;
                default:
                    fragment = new OpenQuestionFragment();
                    break;

            }
        }
        fragment.setArguments(args); //setArgs nes naudojam ta bundle nepamirsti
        return fragment;
    }


}
