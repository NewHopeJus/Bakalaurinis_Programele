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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        Bundle args = new Bundle();

        switch (position) {
            case 0:
                args.putString("levelName", "1 lygis");
                break;
            case 1:
                args.putString("levelName", "2 lygis");
                break;
            case 2:
                args.putString("levelName", "3 lygis");
                break;
            case 3:
                args.putString("levelName", "4 lygis");
                break;
            default:
                args.putString("levelName", "5 lygis");
                break;

        }
        topicsListFragment.setArguments(args);
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_main_menu_list, topicsListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}