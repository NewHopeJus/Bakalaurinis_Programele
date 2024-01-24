package com.mastercoding.bakalaurinis.view.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.view.menus.MainMenuListFragment;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);



        //Pridedam fragmenta su klausimu
        if (savedInstanceState == null) {
            OneSelectionQuestionFragment oneSelectionQuestionFragment = new OneSelectionQuestionFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.question_fragment_container, oneSelectionQuestionFragment)
                    .commit();
        }

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //kad paslepti teksta toolbaro
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}