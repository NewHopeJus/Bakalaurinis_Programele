package com.mastercoding.bakalaurinis.view.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.model.ShopItem;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModel;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class KingdomItemsFragment extends Fragment implements ShopAdapter.ShopItemClickListener {

   // private List<ShopItem> shopItemList = new ArrayList<>();
    private ShopAdapter shopAdapter = new ShopAdapter(this);

    private ShopItemViewModel shopItemViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_kingdom_items, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewFragmentKingdomItems);

        MineSecurityManager securityManager = new MineSecurityManager(requireContext());

        shopItemViewModel = new ViewModelProvider(this, new ShopItemViewModelFactory(securityManager)).get(ShopItemViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Long selectedKingdomId = bundle.getLong("selectedKingdomId");

            shopItemViewModel.getKingdomItems(selectedKingdomId);
            shopItemViewModel.getShopItemListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ShopItemListDto>() {
                @Override
                public void onChanged(ShopItemListDto shopItemListDto) {
                    shopAdapter.clearData();
                   // shopItemList.clear();

                    if (shopItemListDto != null) {
                        //shopItemList.addAll(shopItemListDto.getShopItems());
                        shopAdapter.updateData(shopItemListDto.getShopItems());
                    }
                }


            });
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shopAdapter);


        return rootView;

    }

    @Override
    public void onItemClick(int position) {

    }


}