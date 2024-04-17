package com.mastercoding.bakalaurinis.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityProfileBinding;
import com.mastercoding.bakalaurinis.databinding.ActivityShopBinding;
import com.mastercoding.bakalaurinis.dtos.AccountDeleteRequest;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.PasswordChangeRequest;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.main.MainActivity;
import com.mastercoding.bakalaurinis.view.shop.ShopActivity;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModel;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModelFactory;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding activityProfileBinding;
    private TextView usernameTextView;
    private TextView coinsTextView;
    private TextView experienceTextView;
    private MineSecurityManager securityManager;
    private UserViewModel userViewModel;

    private Button changeUsernameButton;
    private Button changePasswordButton;
    private Button deleteAccoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        Toolbar toolbar = activityProfileBinding.toolbarProfile;
        setSupportActionBar(toolbar);
        //kad paslepti teksta toolbaro
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        usernameTextView = activityProfileBinding.textViewUserNameProfile;
        coinsTextView = activityProfileBinding.textViewCoinsProfile;
        experienceTextView = activityProfileBinding.textViewExperienceProfile;
        changeUsernameButton = activityProfileBinding.buttonChangeUsername;
        changePasswordButton = activityProfileBinding.buttonChangePassword;
        deleteAccoutButton = activityProfileBinding.buttonDeleteAccount;

        MineSecurityManager securityManager = new MineSecurityManager(ProfileActivity.this);
        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(securityManager)).get(UserViewModel.class);

        userViewModel.getUserInfo().observe(ProfileActivity.this, new Observer<UserInfoResponse>() {
            @Override
            public void onChanged(UserInfoResponse userInfoResponse) {
                if (userInfoResponse != null) {
                    usernameTextView.setText(userInfoResponse.getUsername());
                    coinsTextView.setText(String.valueOf(userInfoResponse.getUserCoins()));
                    experienceTextView.setText(String.valueOf(userInfoResponse.getUserExperience()));
                }

            }
        });
        userViewModel.fetchUserInfo();

        userViewModel.getUpdateResponseMutableLiveData().observe(ProfileActivity.this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    Toast.makeText(getApplicationContext(), "Slapyvardis sėkmingai pakeistas!", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Nepavyko pakeisti slapyvardžio. Bandykite dar kartą.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        userViewModel.getUpdatePasswordResponseMutableLiveData().observe(ProfileActivity.this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    Toast.makeText(getApplicationContext(), "Slaptažodis sėkmingai pakeistas!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Nepavyko pakeisti slaptažodžio. Bandykite dar kartą.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        userViewModel.getDeleteAccountMutableLiveData().observe(ProfileActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null){
                    Toast.makeText(getApplicationContext(), "Paskyra sėkmingai ištrinta.", Toast.LENGTH_SHORT).show();
                    securityManager.removeToken();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Nepavyko ištrinti paskyros.", Toast.LENGTH_SHORT).show();

                }
            }
        });


        changeUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                String usernamePattern = "^[a-zA-Z0-9_]{3,20}$";

                builder.setTitle("Slapyvardžio keitimas");

                View customLayout = getLayoutInflater().inflate(R.layout.change_username_custom_layout, null);
                builder.setView(customLayout);
                AlertDialog dialog = builder.create();


                Button saveButton = customLayout.findViewById(R.id.buttonIssaugotiUsernameChange);
                Button cancelBtn = customLayout.findViewById(R.id.buttonAtsauktiUsernameChange);


                EditText naujasSlapyvardis = customLayout.findViewById(R.id.naujasSlapyvardisEditText);
                EditText slaptazodis = customLayout.findViewById(R.id.naujasSlaptazodisEditText);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!naujasSlapyvardis.getText().toString().trim().matches(usernamePattern)) {
                            Toast.makeText(ProfileActivity.this, "Slapyvardį turi sudaryti 3-20 simbolių: raidės, skaičiai arba pabraukimai.", Toast.LENGTH_LONG).show();

                        } else if (!naujasSlapyvardis.getText().toString().trim().isEmpty() && !slaptazodis.getText().toString().trim().isEmpty()) {
                            userViewModel.updateUsername(new LoginRequest(naujasSlapyvardis.getText().toString().trim(), slaptazodis.getText().toString().trim()));
                            dialog.dismiss();

                        } else {
                            Toast.makeText(ProfileActivity.this, "Teisingai įveskite visus duomenis!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                dialog.show();


            }

        });
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Slaptažodžio keitimas");

                View customLayout = getLayoutInflater().inflate(R.layout.change_password_custom_layout, null);
                builder.setView(customLayout);

                Button saveButton = customLayout.findViewById(R.id.buttonIssaugotiPasswordChange);
                Button cancelBtn = customLayout.findViewById(R.id.buttonAtsauktiPasswordChange);

                EditText oldPassword = customLayout.findViewById(R.id.oldPassword);
                EditText newPassword = customLayout.findViewById(R.id.newPassword);
                EditText newPasswordRepeated = customLayout.findViewById(R.id.newPasswordRepeated);


                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_]).{8,}$";

                AlertDialog dialog = builder.create();

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!oldPassword.getText().toString().isEmpty() && !newPassword.getText().toString().isEmpty() && !newPasswordRepeated.getText().toString().isEmpty()) {
                            if (!newPassword.getText().toString().matches(passwordPattern)) {
                                Snackbar.make(v, "Slaptažodį turi sudaryti mažosios, didžiosios raidės, skaičiai ir specialūs simboliai (@#$%^&+=!_).\nSlaptažodis turi būti netrumpesnis nei 8 simboliai.", Snackbar.LENGTH_LONG).setTextMaxLines(5).show();
                            } else if (!newPassword.getText().toString().trim().equals(newPasswordRepeated.getText().toString().trim())) {
                                Toast.makeText(ProfileActivity.this, "Pakartokite naują slaptažodį.", Toast.LENGTH_SHORT).show();
                            } else {
                                userViewModel.updatePassword(new PasswordChangeRequest(oldPassword.getText().toString().trim(), newPassword.getText().toString().trim()));
                                dialog.dismiss();
                            }
                        } else {
                            Toast.makeText(ProfileActivity.this, "Prašome įvesti visus duomenis.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });

        deleteAccoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage("Ar tikrai norite pašalinti savo paskyrą? Po šio veiksmo negalėsite prisijungti prie programėlės su savo prisijungimo duomenimis!")
                        .setTitle("Paskyros šalinimas")
                        .setPositiveButton("Patvirtinti", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                View customLayout = getLayoutInflater().inflate(R.layout.delete_account_custom_layout, null);
                                EditText password = customLayout.findViewById(R.id.editTextSlaptazodisPasalintiPaskyra);

                                AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(ProfileActivity.this);
                                confirmBuilder.setTitle("Pateikite slaptažodį");
                                confirmBuilder.setView(customLayout);
                                confirmBuilder.setPositiveButton("Patvirtinti", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog_remove, int which_remove) {
                                        if(password!=null&& !password.getText().toString().trim().isEmpty()) {
                                            userViewModel.deleteAccount(new AccountDeleteRequest(password.getText().toString().trim()));
                                        }
                                        else{
                                            Toast.makeText(ProfileActivity.this, "Iveskite slaptažodį.", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                                confirmBuilder.setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                confirmBuilder.create().show();
                            }
                        })
                        .setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });


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
        if (item.getItemId() == R.id.action_back || item.getItemId() == R.id.action_home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}