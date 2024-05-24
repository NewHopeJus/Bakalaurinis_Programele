package com.mastercoding.bakalaurinis.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityMainBinding;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModel;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModelFactory;


public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView() method and automatically inflates the layout specified (activity_main),
        // and returns a binding object that can be used to reference any view with an ID within the layout.
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ImageView imageViewWalking = binding.loginPageMonster;
        ImageView owl = binding.loginPageOwl;
        ImageView jelly = binding.loginPageJelly;

        AnimationDrawable walkingAnimation = (AnimationDrawable) imageViewWalking.getBackground();
        walkingAnimation.start();

        AnimationDrawable sleeping = (AnimationDrawable) owl.getBackground();
        sleeping.start();

        AnimationDrawable jellyH = (AnimationDrawable) jelly.getBackground();
        jellyH.start();

        MineSecurityManager securityManager = new MineSecurityManager(MainActivity.this);

        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(securityManager)).get(UserViewModel.class);

        binding.buttonLoginPageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextLoginUsername.getText().toString();
                String password = binding.editTextLoginPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Įveskite prisijungimo duomenis.", Toast.LENGTH_SHORT).show();

                } else {
                    LoginRequest loginRequest = new LoginRequest(username, password);
                    userViewModel.loginUser(loginRequest);
                    if (!userViewModel.getLoginResponseMutableLiveData().hasObservers()) {
                        userViewModel.getLoginResponseMutableLiveData().observe(MainActivity.this, new Observer<LoginResponse>() {
                            @Override
                            public void onChanged(LoginResponse loginResponse) {
                                if (loginResponse != null && "User is blocked.".equals(loginResponse.getJwt())) {
                                    Toast.makeText(MainActivity.this, "Vartotojas yra užblokuotas.", Toast.LENGTH_LONG).show();
                                } else if (loginResponse != null && loginResponse.getJwt() != null && !loginResponse.getJwt().isEmpty()) {
                                    Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Nesėkmingas prisijungimas, bandykite dar kartą.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            }

        });

        binding.buttonLoginPageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}