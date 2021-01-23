package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.FlashDealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlashDealApiInterface {
    @GET("products/flash-deal")
    Call<FlashDealResponse> getFlashDeal();
}