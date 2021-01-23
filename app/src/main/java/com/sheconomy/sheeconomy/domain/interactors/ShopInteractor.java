package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Shop;

public interface ShopInteractor {
    interface CallBack {

        void onShopLoaded(Shop shop);

        void onShopLoadError();
    }
}
