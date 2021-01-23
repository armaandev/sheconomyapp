package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.MessageResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CartQuantityApiInterface {
    @Headers("Content-Type: application/json")
    @POST("carts/change-quantity")
    Call<MessageResponse> sendUpdateCartQuantutyRequest(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}
