package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.OrderResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WalletApiInterface {
    @Headers("Content-Type: application/json")
    @POST("payments/pay/wallet")
    Call<OrderResponse> sendPlaceOrderRequest(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}