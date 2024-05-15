package com.mastercoding.bakalaurinis.view.ranking;

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
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.ActivityRankingBinding;
import com.mastercoding.bakalaurinis.databinding.ActivityStatisticsBinding;
import com.mastercoding.bakalaurinis.dtos.RankingDto;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.statistics.StatisticsActivity;
import com.mastercoding.bakalaurinis.view.statistics.StatisticsAdapter;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModel;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModelFactory;
import com.mastercoding.bakalaurinis.viewmodel.RankingViewModel;
import com.mastercoding.bakalaurinis.viewmodel.RankingViewModelFactory;

import java.util.List;
import java.util.Objects;

public class RankingActivity extends AppCompatActivity {
    private RankingViewModel rankingViewModel;
    private RankingAdapter rankingAdapter = new RankingAdapter();

    private ActivityRankingBinding activityRankingBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityRankingBinding = DataBindingUtil.setContentView(this, R.layout.activity_ranking);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //kad paslepti teksta toolbaro
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        MineSecurityManager securityManager = new MineSecurityManager(this);

        RecyclerView recyclerView = activityRankingBinding.recyclerViewRanking;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rankingAdapter);

        ImageView star =  activityRankingBinding.imageViewStar;

        YoYo.with(Techniques.Flash)
                .duration(700)
                .repeat(2)
                .playOn(star);



        rankingViewModel = new ViewModelProvider(this, new RankingViewModelFactory(securityManager)).get(RankingViewModel.class);
        rankingViewModel.getRankingList();

        rankingViewModel.getRankings().observe(this, new Observer<List<RankingDto>>() {
            @Override
            public void onChanged(List<RankingDto> rankingDtos) {
                rankingAdapter.updateData(rankingDtos);
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