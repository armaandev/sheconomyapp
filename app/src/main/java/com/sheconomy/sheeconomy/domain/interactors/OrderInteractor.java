package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.OrderResponse;

public interface OrderInteractor {
    interface CallBack {

        void onOrderSubmitted(OrderResponse orderResponse);

        void onOrderSubmitError();
    }
}
