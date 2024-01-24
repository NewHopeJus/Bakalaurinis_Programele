package com.mastercoding.bakalaurinis.view.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.mastercoding.bakalaurinis.R;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //pirma fragmenta pridedam su main menu listu
        if (savedInstanceState == null) {
            MainMenuListFragment mainMenuListFragment = new MainMenuListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_menu_list, mainMenuListFragment)
                    .addToBackStack("home") //nes reikes grizti
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //kad paslepti teksta toolbaro
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        if (item.getItemId() == R.id.action_back) {
            //nes jei maziau uz viena tai removina visus fragmentus
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
                return true;
            }
            return true;
        } else if (item.getItemId() == R.id.action_home) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
              //0: This flag means that the operation will only pop the back stack up to the
                // given fragment (in this case, "home" fragment).
                getSupportFragmentManager().popBackStack("home", 0);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
