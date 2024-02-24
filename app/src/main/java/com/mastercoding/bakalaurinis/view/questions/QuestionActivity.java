package com.mastercoding.bakalaurinis.view.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityQuestionBinding;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.main.MainActivity;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModel;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModelFactory;

import java.util.Objects;


public class QuestionActivity extends AppCompatActivity {

    private QuestionViewModel questionViewModel;

    private ActivityQuestionBinding activityQuestionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_question);

        String levelName = getIntent().getStringExtra("levelName");
        String topicName = getIntent().getStringExtra("topicName");

        MineSecurityManager securityManager = new MineSecurityManager(QuestionActivity.this);

        questionViewModel = new ViewModelProvider(this, new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);


        if (questionViewModel.getQuestionLiveData() == null) { //tikrinimas ar perkraunam nes kitu aveju jei rotatina screena nereikia
            questionViewModel.getQuestion();
        }

        questionViewModel.getQuestionLiveData().observe(this,
                new Observer<Question>() {
                    @Override
                    public void onChanged(Question question) {

                        if (!questionViewModel.isAnswered()) {
                            Fragment fragment = FragmentLoadingService.loadQuestionFragment(question);
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container_question_fragment, fragment)
                                    .commit();
                        }

                    }
                });


        Toolbar toolbar = activityQuestionBinding.toolbarQuestionActivity;
        setSupportActionBar(toolbar);

        //kad paslepti teksta toolbaro
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
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

