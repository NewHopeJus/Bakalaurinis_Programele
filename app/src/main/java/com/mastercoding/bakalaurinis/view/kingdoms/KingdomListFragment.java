package com.mastercoding.bakalaurinis.view.kingdoms;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastercoding.bakalaurinis.R;

import java.util.ArrayList;
import java.util.List;

public class KingdomListFragment extends Fragment implements CustomAdapter.ItemClickListener {

    private List<Kingdom> kingdomList;
    private CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kingdom_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewFragmentKingdomList);

        kingdomList = new ArrayList<>();

        Kingdom kingdom0 = new Kingdom("Mano pilis", R.drawable.mano_pilis);
        Kingdom kingdom1 = new Kingdom("Gėlių karalystė", R.drawable.geliu_karalyste);
        Kingdom kingdom4 = new Kingdom("Povandeninė karalystė", R.drawable.povandenine_karalyste);
        Kingdom kingdom3 = new Kingdom("Grybų karalystė", R.drawable.grybu_karalyste);
        Kingdom kingdom2 = new Kingdom("Elfų karalystė", R.drawable.elfu_karalyste);
        Kingdom kingdom5 = new Kingdom("Saldumynų karalystė", R.drawable.saldumynu_karalyste);
        Kingdom kingdom6 = new Kingdom("Nykštukų karalystė", R.drawable.nykstuku_karalyste);

        kingdomList.add(kingdom0);
        kingdomList.add(kingdom1);
        kingdomList.add(kingdom2);
        kingdomList.add(kingdom3);
        kingdomList.add(kingdom4);
        kingdomList.add(kingdom5);
        kingdomList.add(kingdom6);

        customAdapter = new CustomAdapter(kingdomList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(customAdapter);


        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        Fragment fragment = new KingdomViewFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("kingdomId", position);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container_kingdom_list, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}