package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.ShippingInfoResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ShippingInfoCreateApiInterface {
    @POST("user/shipping/create")
    Call<ShippingInfoResponse> updateShippingInfo(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}
