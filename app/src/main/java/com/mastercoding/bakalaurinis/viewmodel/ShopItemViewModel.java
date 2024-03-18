package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.repository.ShopItemRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class ShopItemViewModel extends ViewModel {
    private MutableLiveData<ShopItemListDto> shopItemListMutableLiveData;

    private ShopItemRepository shopItemRepository;

    public ShopItemViewModel(MineSecurityManager securityManager) {
        this.shopItemRepository = new ShopItemRepository(securityManager);
    }

    public void getKingdomItems(Long id){
        this.shopItemListMutableLiveData = shopItemRepository.getShopItemsForKingdom(id);
    }

    public MutableLiveData<ShopItemListDto> getShopItemListMutableLiveData() {
        return shopItemListMutableLiveData;
    }


}
