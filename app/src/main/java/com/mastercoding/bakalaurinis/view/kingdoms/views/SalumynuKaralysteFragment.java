package com.mastercoding.bakalaurinis.view.kingdoms.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentPovandenineKaralysteBinding;
import com.mastercoding.bakalaurinis.databinding.FragmentSaldumynuKaralysteBinding;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.kingdoms.views.utils.ShopItemVisibilityUtil;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModel;
import com.mastercoding.bakalaurinis.viewmodel.ShopItemViewModelFactory;


public class SalumynuKaralysteFragment extends Fragment {

    private FragmentSaldumynuKaralysteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSaldumynuKaralysteBinding.inflate(inflater, container, false);
        MineSecurityManager securityManager = new MineSecurityManager(requireContext());
        ShopItemViewModel shopItemViewModel = new ViewModelProvider(this, new ShopItemViewModelFactory(securityManager)).get(ShopItemViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Long selectedKingdomId = bundle.getLong("kingdomId");
            if (selectedKingdomId != -1) {
                shopItemViewModel.getBoughtItemsByKingdomId(selectedKingdomId);


                shopItemViewModel.getBoughtItemListLiveData().observe(getViewLifecycleOwner(), new Observer<ShopItemListDto>() {
                    @Override
                    public void onChanged(ShopItemListDto shopItemListDto) {
                        ShopItemVisibilityUtil.setBoughtItemsVisibility(binding.getRoot(), shopItemListDto, getContext());
                    }
                });
            }
        }

        return binding.getRoot();
    }
}