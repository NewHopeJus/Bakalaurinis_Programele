package com.mastercoding.bakalaurinis.view.main_menu;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;


public class LevelsListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String[] mainMenuArray = {"1 lygis", "2 lygis", "3 lygis", "4 lygis", "5 lygis"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), R.layout.fragment_main_menu_list,
                mainMenuArray);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setPadding(100, 0, 100, 20); // Left, Top, Right, Bottom padding in pixels
        listView.setClipToPadding(false);
    }





}