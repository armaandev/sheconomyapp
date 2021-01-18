package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.PurchaseHistoryDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface PurchaseHistoryDetailsApiInterface {
    @GET
    Call<PurchaseHistoryDetailsResponse> getOrderItems(@Header("Authorization") String authHeader, @Url String url);
}
