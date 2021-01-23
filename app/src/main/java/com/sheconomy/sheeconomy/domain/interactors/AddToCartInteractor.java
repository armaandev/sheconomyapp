package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.AddToCartResponse;

public interface AddToCartInteractor {
    interface CallBack {

        void onCartItemAdded(AddToCartResponse addToCartResponse);

        void onCartItemAddedError();
    }
}
