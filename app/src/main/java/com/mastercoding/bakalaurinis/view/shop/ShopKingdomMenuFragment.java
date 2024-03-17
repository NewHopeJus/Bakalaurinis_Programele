package com.mastercoding.bakalaurinis.view.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.KingdomDto;
import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.kingdoms.CustomAdapter;
import com.mastercoding.bakalaurinis.view.kingdoms.KingdomMenuActivity;
import com.mastercoding.bakalaurinis.view.kingdoms.KingdomViewFragment;
import com.mastercoding.bakalaurinis.view.menus.LevelsListFragment;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModel;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class ShopKingdomMenuFragment extends Fragment implements CustomAdapter.ItemClickListener{


    private List<KingdomDto> kingdomList =  new ArrayList<>();
    private CustomAdapter customAdapter = new CustomAdapter(this);
    private KingdomViewModel kingdomViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shop_kingdom_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewShopFragmentKingdomList);


        kingdomList = new ArrayList<>();


        MineSecurityManager securityManager = new MineSecurityManager(requireContext());

        kingdomViewModel = new ViewModelProvider(this, new KingdomViewModelFactory(securityManager)).get(KingdomViewModel.class);

        if(kingdomViewModel.getKingdomListsResponseLiveData()==null){
            kingdomViewModel.getKingdoms();
        }

        kingdomViewModel.getKingdomListsResponseLiveData().observe(getViewLifecycleOwner(), new Observer<KingdomListsResponse>() {

            @Override
            public void onChanged(KingdomListsResponse kingdomListsResponse) {

                kingdomList.addAll(kingdomListsResponse.getOpenedKingdoms());
                kingdomList.addAll(kingdomListsResponse.getClosedKingdoms());
                customAdapter.updateData(kingdomList);
            }
        });

        recyclerView.setAdapter(customAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onItemClick(int position) {

        KingdomDto selectedKingdom = kingdomList.get(position);
        Long kingdomId = selectedKingdom.getId();

        FragmentManager manager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        Fragment fragment = new KingdomItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("selectedKingdomId", kingdomId);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container_shop_kingdom_list, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}