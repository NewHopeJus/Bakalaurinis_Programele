package com.mastercoding.bakalaurinis.view.menus;

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
import com.mastercoding.bakalaurinis.data.LevelsData;


public class LevelsListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), R.layout.fragment_main_menu_list,
                new LevelsData().getLevelNames());
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);


    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setPadding(100, 0, 100, 20); // Left, Top, Right, Bottom padding in pixels
        listView.setClipToPadding(false);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        TopicsListFragment topicsListFragment = new TopicsListFragment();

        //duomenu struktura kuri leidzia passinnti duomenis tarp skirtingu activities ir fragmentu
        Bundle args = new Bundle();

        if(position == 0){
            args.putString("levelName", "1 lygis"); // idedam kad zinot poto koki lygi
            topicsListFragment.setArguments(args);

            FragmentManager manager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= manager.beginTransaction();
            //replacinam fragmenta 1 lygio i jo topicu
            //pirmas parametras - konteineris fragmentams
            // antras parametras naujas fragmentas

            fragmentTransaction.replace(R.id.fragment_container_menu_list,topicsListFragment);
            fragmentTransaction.addToBackStack(null); //nes reikes veliau atgal
            fragmentTransaction.commit();
        }

        if(position == 1){
            args.putString("levelName", "2 lygis"); // idedam kad zinot poto koki lygi
            topicsListFragment.setArguments(args);

            FragmentManager manager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= manager.beginTransaction();
            //replacinam fragmenta 1 lygio i jo topicu
            //pirmas parametras - konteineris fragmentams
            // antras parametras naujas fragmentas

            fragmentTransaction.replace(R.id.fragment_container_menu_list,topicsListFragment);
            fragmentTransaction.addToBackStack(null); //nes reikes veliau atgal
            fragmentTransaction.commit();
        }




    }




}