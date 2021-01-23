package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.AddToWishlistResponse;

public interface AddToWishlistInteractor {
    interface CallBack {

        void onWishlistItemAdded(AddToWishlistResponse addToWishlistResponse);

        void onWishlistItemAddedError();
    }
}
