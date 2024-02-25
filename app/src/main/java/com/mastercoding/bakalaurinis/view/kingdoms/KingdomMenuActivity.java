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

        //kad paslepti teksta toolbaro
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbaro menu pridedam
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // atgal mygtukas ant toolbaro arba home buttonas
        //pakeistiiiii!! kai fragmentai bus
            finish();
        return super.onOptionsItemSelected(item);
    }
}