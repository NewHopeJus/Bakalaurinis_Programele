package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.BuyItemResponse;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.repository.ShopItemRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class ShopItemViewModel extends ViewModel {
    private MutableLiveData<ShopItemListDto> shopItemListMutableLiveData ;

    private MutableLiveData<BuyItemResponse> buyItemResponseLiveData;

    private LiveData<ShopItemListDto> boughtItemListMutableLiveData;


    private ShopItemRepository shopItemRepository;

    public ShopItemViewModel(MineSecurityManager securityManager) {
        this.shopItemRepository = new ShopItemRepository(securityManager);
        this.boughtItemListMutableLiveData= shopItemRepository.getBoughtItemsLiveData();
    }

    public void getKingdomItems(Long id){
        this.shopItemListMutableLiveData = shopItemRepository.getShopItemsForKingdom(id);
    }

    public MutableLiveData<ShopItemListDto> getShopItemListMutableLiveData() {
        return shopItemListMutableLiveData;
    }

    public void buyItem(Long id){
        this.buyItemResponseLiveData = shopItemRepository.buyItem(id);
    }

    public MutableLiveData<BuyItemResponse> getBuyItemResponseLiveData() {
        return buyItemResponseLiveData;
    }

    public void resetBuyItemResponseLiveData(){
        buyItemResponseLiveData.setValue(null);
    }

    public void getBoughtItemsByKingdomId(Long id){
        shopItemRepository.getBoughtItemsByKingdomId(id);
    }

    public LiveData<ShopItemListDto> getBoughtItemListLiveData() {
        return boughtItemListMutableLiveData;
    }
}
