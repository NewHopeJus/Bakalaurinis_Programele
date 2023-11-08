package com.mastercoding.bakalaurinis.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityMainBinding;
import com.mastercoding.bakalaurinis.databinding.ActivityRegisterBinding;
import com.mastercoding.bakalaurinis.model.User;
import com.mastercoding.bakalaurinis.retrofit.RetrofitClientInstance;
import com.mastercoding.bakalaurinis.retrofit.UserService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);



        binding.buttonRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User(binding.editTextRegisterUsername.getText().toString(), binding.editTextRegisterEmail.getText().toString(),
                        binding.editTextRegisterPassword.getText().toString(), "USER");

                System.out.println(user.getUsername());
                registerUser(user);

            }
        });

        binding.buttonRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(User user) {
        Call<User> call = userService.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d("RegisterActivity", "Registration Successful");
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("RegisterActivity", "Registration Failed. Response Code: " + response.code());
                    Toast.makeText(RegisterActivity.this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("RegisterActivity", "Registration Failed", t);
                Toast.makeText(RegisterActivity.this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });



    }
}