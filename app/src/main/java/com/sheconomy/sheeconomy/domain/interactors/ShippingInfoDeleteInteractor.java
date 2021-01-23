package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.ShippingInfoResponse;

public interface ShippingInfoDeleteInteractor {
    interface CallBack {

        void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse);

        void onShippingInfoDeleteError();
    }
}
