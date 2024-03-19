package com.mastercoding.bakalaurinis.view.shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.BuyItemResponse;
import com.mastercoding.bakalaurinis.model.ShopItem;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModel;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class KingdomItemsFragment extends Fragment implements ShopAdapter.ShopItemClickListener {

    private List<ShopItem> shopItemList = new ArrayList<>();
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


            if (shopItemViewModel.getShopItemListMutableLiveData() == null) {
                shopItemViewModel.getKingdomItems(selectedKingdomId);
            }

        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(shopAdapter);


        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopItemViewModel.getShopItemListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ShopItemListDto>() {
            @Override
            public void onChanged(ShopItemListDto shopItemListDto) {
                shopAdapter.clearData();

                if (shopItemListDto != null) {
                    shopItemList.addAll(shopItemListDto.getShopItems());
                    shopAdapter.updateData(shopItemList);
                }
            }

        });


    }

    @Override
    public void onItemClick(int position) {
        Long itemId = shopItemList.get(position).getId();
        shopItemViewModel.buyItem(itemId);
            shopItemViewModel.getBuyItemResponseLiveData().observe(getViewLifecycleOwner(), new Observer<BuyItemResponse>() {
                @Override
                public void onChanged(BuyItemResponse buyItemResponse) {
                    if (buyItemResponse != null) {

                        Toast.makeText(requireContext(), buyItemResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        shopItemViewModel.getBuyItemResponseLiveData().removeObservers(getViewLifecycleOwner());
                        shopItemViewModel.resetBuyItemResponseLiveData();
                    }

                }
            });
        }



}