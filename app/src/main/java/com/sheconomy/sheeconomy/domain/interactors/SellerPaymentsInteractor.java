package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Banner;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Models.Shop;
import com.sheconomy.sheeconomy.Network.response.SellerPaymentResponse;

import java.util.List;

public interface SellerPaymentsInteractor {
    interface CallBack {

//        void onSellerPaymentsLoaded(SellerPayments sellerPayments);
        void onSellerPaymentsLoaded(List<SellerPayments> sellerPayments);

        void onSellerPaymentsLoadError();
    }
}
