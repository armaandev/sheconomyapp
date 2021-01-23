package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.OrderResponse;

public interface PaypalInteractor {
    interface CallBack {

        void onPayaplOrderSubmitted(OrderResponse orderResponse);

        void onPayaplOrderSubmitError();
    }
}
