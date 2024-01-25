package com.mastercoding.bakalaurinis.view.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;
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
                    .add(R.id.fragment_container_question_fragment, oneSelectionQuestionFragment)
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbarQuestionActivity);
        setSupportActionBar(toolbar);

        //kad paslepti teksta toolbaro
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbaro menu pridedam
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // atgal mygtukas ant toolbaro
        //Griztam i lygio temu langa
        if (item.getItemId() == R.id.action_back) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_home) {
           //Griztam i pagrindini puslapi
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("home", 0);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}