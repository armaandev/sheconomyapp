package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.SellerPaymentResponse;
import com.sheconomy.sheeconomy.Network.response.ShopResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface SellerPaymentApiInterface {
    @GET("seller-payment/{id}")
    Call<SellerPaymentResponse> getSellerPayment(@Path("id") int sellerId);

//    @GET("seller-payment")
//    Call<SellerPaymentResponse> getSellerPayment();

}