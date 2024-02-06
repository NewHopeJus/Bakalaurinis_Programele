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

public class MainMenuListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String[] mainMenuArray = {"Spręsti", "Karalystės", "Parduotuvė", "Sprendimų statistika", "Profilis"};

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
        listView.setPadding(100, 0, 100, 20); // kad prideti tarpus prie pagrindinio menu listo is sonu
        listView.setClipToPadding(false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //jei pasirinko spresti
        if(position == 0){
            LevelsListFragment levelsListFragment = new LevelsListFragment();
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= manager.beginTransaction();
           //replacinam fragmenta is main menu su lygiais
            //pirmas parametras - konteineris fragmentams
            // antras parametras naujas fragmentas
            fragmentTransaction.replace(R.id.fragment_container_menu_list,levelsListFragment);
            fragmentTransaction.addToBackStack("levels"); //nes reikes veliau atgal
            fragmentTransaction.commit();
        }


    }
}