package com.mastercoding.bakalaurinis.view.menus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.data.LevelsData;

import java.util.List;


public class TopicsListFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String levelName = getArguments().getString("levelName", ""); //gaunam argumenta kuri idejom, levelio pavadinima

        // gaunam temas
        LevelsData levelsData = new LevelsData();
        List<String> topics = levelsData.getTopicsForLevel(levelName);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), R.layout.topics_list,
                topics);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}