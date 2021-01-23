package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.MessageResponse;

public interface CartQuantityInteractor {
    interface CallBack {

        void onCartQuantityUpdated(MessageResponse messageResponse);

        void onCartQuantityUpdatedError();
    }
}
