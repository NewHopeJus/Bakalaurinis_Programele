package com.mastercoding.bakalaurinis.view.menus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityMainMenuBinding;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModel;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModelFactory;

import java.util.Objects;

public class MainMenuActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    private ActivityMainMenuBinding activityMainMenuBinding;
    private TextView usernameTextView;
    private TextView coinsTextView;
    private TextView experienceTextView;
    private MineSecurityManager securityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);

        usernameTextView = activityMainMenuBinding.textViewUserNameMainMenu;
        coinsTextView = activityMainMenuBinding.textViewCoinsMainMenu;
        experienceTextView = activityMainMenuBinding.textViewExperienceMainMenu;
        securityManager = new MineSecurityManager(MainMenuActivity.this);

        if (savedInstanceState == null) {
            MainMenuListFragment mainMenuListFragment = new MainMenuListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_main_menu_list, mainMenuListFragment)
                    .addToBackStack("home")
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        MineSecurityManager securityManager = new MineSecurityManager(MainMenuActivity.this);
        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(securityManager)).get(UserViewModel.class);

        userViewModel.fetchUserInfo();
        userViewModel.getUserInfo().observe(MainMenuActivity.this, new Observer<UserInfoResponse>() {
            @Override
            public void onChanged(UserInfoResponse userInfoResponse) {
                if (userInfoResponse != null) {
                    usernameTextView.setText(userInfoResponse.getUsername());
                    coinsTextView.setText(String.valueOf(userInfoResponse.getUserCoins()));
                    experienceTextView.setText(String.valueOf(userInfoResponse.getUserExperience()));
                }
            }
        });

        ImageView jelly = activityMainMenuBinding.imageView15;
        AnimationDrawable walkingAnimation = (AnimationDrawable) jelly.getBackground();
        walkingAnimation.start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(jelly, "translationX", 0f, 1000f);
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_back) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
                return true;
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuActivity.this)
                        .setTitle("Atsijungimas")
                        .setMessage("Ar tikrai norite atsijungti nuo paskyros?");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        securityManager.removeToken();
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            return true;
        } else if (item.getItemId() == R.id.action_home) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                //0: This flag means that the operation will only pop the back stack up to the
                // given fragment (in this case, "home" fragment).
                getSupportFragmentManager().popBackStack("home", 0);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.fetchUserInfo();
        if (!userViewModel.getUserInfo().hasActiveObservers()) {
            userViewModel.getUserInfo().observe(MainMenuActivity.this, new Observer<UserInfoResponse>() {
                @Override
                public void onChanged(UserInfoResponse userInfoResponse) {
                    usernameTextView.setText(userInfoResponse.getUsername());
                    coinsTextView.setText(String.valueOf(userInfoResponse.getUserCoins()));
                    experienceTextView.setText(String.valueOf(userInfoResponse.getUserExperience()));
                }
            });
        }
    }
}
