package com.mastercoding.bakalaurinis.view.menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.view.ProfileActivity;
import com.mastercoding.bakalaurinis.view.kingdoms.KingdomListFragment;
import com.mastercoding.bakalaurinis.view.kingdoms.KingdomMenuActivity;
import com.mastercoding.bakalaurinis.view.main.MainActivity;
import com.mastercoding.bakalaurinis.view.ranking.RankingActivity;
import com.mastercoding.bakalaurinis.view.shop.ShopActivity;
import com.mastercoding.bakalaurinis.view.statistics.StatisticsActivity;

public class MainMenuListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] mainMenuArray = {"Spręsti", "Karalystės", "Parduotuvė", "Sprendimų statistika", "Profilis", "Lyderiai"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), R.layout.fragment_main_menu_list,
                mainMenuArray);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setPadding(100, 0, 100, 20);
        listView.setClipToPadding(false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //If the "Spresti" option from main menu was chosen
        if (position == 0) {
            LevelsListFragment levelsListFragment = new LevelsListFragment();
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();

            //Replacing fragment with levels fragment
            //First parameter - container for the fragment
            //Second parameter - new fragment
            fragmentTransaction.replace(R.id.fragment_container_main_menu_list, levelsListFragment);
            fragmentTransaction.addToBackStack("levels");
            fragmentTransaction.commit();

        } else if (position == 1) {
            Intent intent = new Intent(getActivity(), KingdomMenuActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(getActivity(), ShopActivity.class);
            startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(getActivity(), StatisticsActivity.class);
            startActivity(intent);
        } else if (position == 4) {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), RankingActivity.class);
            startActivity(intent);
        }
    }
}