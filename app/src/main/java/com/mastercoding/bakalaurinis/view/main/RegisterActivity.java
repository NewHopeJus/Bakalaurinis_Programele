package com.mastercoding.bakalaurinis.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityRegisterBinding;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.model.User;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.retrofit.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private final Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
    private final UserAPI userService = retrofit.create(UserAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        ImageView imageViewWalking = binding.registerMonster;
        AnimationDrawable walkingAnimation = (AnimationDrawable) imageViewWalking.getBackground();
        walkingAnimation.start();

        ImageView jelly = binding.registerJelly;

        AnimationDrawable jellyAnimation = (AnimationDrawable) jelly.getBackground();
        jellyAnimation.start();

        binding.buttonRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.editTextRegisterUsername.getText().toString();
                String password = binding.editTextRegisterPassword.getText().toString();
                String passwordRepeated = binding.editTextRegisterRetypePassword.getText().toString();

                String usernamePattern = "^[a-zA-Z0-9_]{3,20}$";

                // Requires at least one lowercase letter, one uppercase letter, one digit,
                // one special character from the set @#$%^&+=!_, and a minimum length of 8 characters.
                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_]).{8,}$";

                if (!username.isEmpty() && username.matches(usernamePattern)) {
                    if (!password.isEmpty() && password.matches(passwordPattern)) {
                        if (!passwordRepeated.isEmpty() && password.equals(passwordRepeated)) {
                            LoginRequest user = new LoginRequest(username, password);
                            registerUser(user);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Pakartokite slaptažodį", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Netinkamas slaptažodis", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Vartotojo vardą turi sudaryti 3-20 simbolių: raidės, skaičiai arba pabraukimai.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //returnina i logino activity
            }
        });

        binding.infoPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showInfoPopup(view, "Slaptažodį turi sudaryti mažosios, didžiosios raidės, skaičiai ir specialūs simboliai (@#$%^&+=!_). \n" +
                        "Slaptažodis turi būti netrumpesnis nei 8 simboliai.");
            }
        });

        binding.infoPasswordRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoPopup(view, "Pakartotinai įveskite norimą naudoti slaptažodį ir paspauskite registruotis.");
            }
        });
    }

    private void registerUser(LoginRequest user) {
        Call<LoginRequest> call = userService.registerUser(user);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                if (response.isSuccessful()) {
                    Log.d("RegisterActivity", "Registration Successful");
                    Toast.makeText(RegisterActivity.this, "Registracija sėkminga. Prisijunkite.", Toast.LENGTH_SHORT).show();
                    finish(); //griztam i logino puslapi

                } else {
                    Log.d("RegisterActivity", "Registration was not successful. Response Code: " + response.code());
                    Toast.makeText(RegisterActivity.this, "Nesėkminga registracija. Bandykite dar kartą.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
                Log.e("RegisterActivity", "Registration Failed", t);
                Toast.makeText(RegisterActivity.this, "Nesėkminga registracija. Bandykite dar kartą", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInfoPopup(View anchorView, String popupText) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView popupTextView = popupView.findViewById(R.id.popupTextView);
        popupTextView.setText(popupText);
        popupWindow.showAsDropDown(anchorView);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
