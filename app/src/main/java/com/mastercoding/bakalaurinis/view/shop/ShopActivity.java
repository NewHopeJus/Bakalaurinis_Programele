package com.mastercoding.bakalaurinis.view.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityShopBinding;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModel;
import com.mastercoding.bakalaurinis.viewmodel.UserViewModelFactory;

import java.util.Objects;

public class ShopActivity extends AppCompatActivity {
    private ActivityShopBinding activityShopBinding;
    private TextView usernameTextView;
    private TextView coinsTextView;
    private TextView experienceTextView;
    private MineSecurityManager securityManager;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        activityShopBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop);

        usernameTextView = activityShopBinding.textViewUserNameShop;
        coinsTextView = activityShopBinding.textViewCoinsShop;
        experienceTextView = activityShopBinding.textViewExperienceShop;

        securityManager = new MineSecurityManager(ShopActivity.this);

        if (savedInstanceState == null) {
            ShopKingdomMenuFragment shopKingdomMenuFragment = new ShopKingdomMenuFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_shop_kingdom_list, shopKingdomMenuFragment)
                    .addToBackStack("shop_kingdom_list")
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        MineSecurityManager securityManager = new MineSecurityManager(ShopActivity.this);
        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(securityManager)).get(UserViewModel.class);

        userViewModel.fetchUserInfo();
        userViewModel.getUserInfo().observe(ShopActivity.this, new Observer<UserInfoResponse>() {
            @Override
            public void onChanged(UserInfoResponse userInfoResponse) {
                if (userInfoResponse != null) {
                    usernameTextView.setText(userInfoResponse.getUsername());
                    coinsTextView.setText(String.valueOf(userInfoResponse.getUserCoins()));
                    experienceTextView.setText(String.valueOf(userInfoResponse.getUserExperience()));
                }
            }
        });
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
            } else {
                finish();
            }
            return true;
        } else if (item.getItemId() == R.id.action_home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.fetchUserInfo();
        if (!userViewModel.getUserInfo().hasActiveObservers()) {
            userViewModel.getUserInfo().observe(ShopActivity.this, new Observer<UserInfoResponse>() {
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