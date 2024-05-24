package com.mastercoding.bakalaurinis.view.kingdoms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.view.menus.MainMenuActivity;
import com.mastercoding.bakalaurinis.view.menus.MainMenuListFragment;

import java.util.Objects;

public class KingdomMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom_menu);

        if (savedInstanceState == null) {
            KingdomListFragment kingdomListFragment = new KingdomListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_kingdom_list, kingdomListFragment)
                    .addToBackStack("kingdom_list")
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hiding toolbar text
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toolbar button back
        if (item.getItemId() == R.id.action_back) {
            //If less than 1 all fragments are removed
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
}