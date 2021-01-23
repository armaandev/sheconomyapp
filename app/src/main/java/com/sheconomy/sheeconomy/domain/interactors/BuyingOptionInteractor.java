package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.VariantResponse;

public interface BuyingOptionInteractor {
    interface CallBack {

        void onGetVariantPrice(VariantResponse variantResponse);

        void onGetVariantPriceError();
    }
}
