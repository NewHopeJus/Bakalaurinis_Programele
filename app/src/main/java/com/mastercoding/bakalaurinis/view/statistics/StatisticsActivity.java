package com.mastercoding.bakalaurinis.view.statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityShopBinding;
import com.mastercoding.bakalaurinis.databinding.ActivityStatisticsBinding;
import com.mastercoding.bakalaurinis.dtos.KingdomDto;
import com.mastercoding.bakalaurinis.dtos.LevelStatisticsDto;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.dtos.StatisticsResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.kingdoms.CustomAdapter;
import com.mastercoding.bakalaurinis.view.shop.ShopActivity;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModel;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModel;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModelFactory;
import com.mastercoding.bakalaurinis.viewmodel.StatisticsViewModel;
import com.mastercoding.bakalaurinis.viewmodel.StatisticsViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsAdapter statisticsAdapter = new StatisticsAdapter();
    private StatisticsViewModel statisticsViewModel;
    private ActivityStatisticsBinding activityStatisticsBinding;
    private MineSecurityManager securityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStatisticsBinding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);
        securityManager = new MineSecurityManager(this);
        Toolbar toolbar = activityStatisticsBinding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        TextView correctAnswered = activityStatisticsBinding.textViewTotalCorrect;
        TextView incorrectAnswered = activityStatisticsBinding.textViewTotalIncorrect;

        RecyclerView recyclerView = activityStatisticsBinding.recyclerViewStatisticsActivity;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(statisticsAdapter);

        statisticsViewModel = new ViewModelProvider(this, new StatisticsViewModelFactory(securityManager)).get(StatisticsViewModel.class);
        statisticsViewModel.getStatistics();

        statisticsViewModel.getStatisticsResponseMutableLiveData().observe(this, statisticsResponse -> {
            if (statisticsResponse != null) {
                statisticsAdapter.updateData(statisticsResponse.getLevelsStatistics());
                correctAnswered.setText(String.valueOf(statisticsResponse.getTotalCorrectlyAnswered()));
                Integer incorrect = statisticsResponse.getTotalAnswered() - statisticsResponse.getTotalCorrectlyAnswered();
                incorrectAnswered.setText(String.valueOf(incorrect));
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
        if (item.getItemId() == R.id.action_back || item.getItemId() == R.id.action_home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}