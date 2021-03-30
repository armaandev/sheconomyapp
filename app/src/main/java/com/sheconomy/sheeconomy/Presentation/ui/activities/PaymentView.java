package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Network.response.CouponResponse;
import com.sheconomy.sheeconomy.Network.response.OrderResponse;

import java.util.List;

public interface PaymentView {
    void onCouponApplied(CouponResponse couponResponse);
    void onOrderSubmitted(OrderResponse orderResponse);
    //new line added for sellerPayments key by armaan
//    void onSellerDetailsLoaded(List<SellerPayments> sellerPayments);
//    void toMakePayments(SellerPayments sellerPayments);
}
