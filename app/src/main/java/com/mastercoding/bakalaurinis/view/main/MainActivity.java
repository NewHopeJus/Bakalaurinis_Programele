package com.mastercoding.bakalaurinis.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityMainBinding;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.model.User;
import com.mastercoding.bakalaurinis.retrofit.RetrofitClientInstance;
import com.mastercoding.bakalaurinis.retrofit.UserService;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView() method and automatically inflates the layout specified (activity_main),
        // and returns a binding object that can be used to reference any view with an ID within the layout.
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonLoginPageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextLoginUsername.getText().toString();
                String password = binding.editTextLoginPassword.getText().toString();
                User user = new User(username, password);
                loginUser(user);
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

    //bendravimas su api
    private void loginUser(User user) {
        Call<LoginResponse> call = userService.loginUser(user);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getJwt().isEmpty())

                        editor.putString("jwt_token", response.body().getJwt()); //nes reikes tokena deti i kiekviena requesta todel ji issaugom
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("Login", "Login failed. Bad token. " + response.code());
                    Toast.makeText(MainActivity.this, "Nesėkmingas prisijungimas, bandykite dar kartą.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d("Login", "Login failed " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Nesekmingas prisijungimas", Toast.LENGTH_SHORT).show();

            }
        });


    }
}