package com.sheconomy.sheeconomy.Network.services;

import com.google.gson.JsonObject;
import com.sheconomy.sheeconomy.Models.Currency;
import com.sheconomy.sheeconomy.Network.response.BannerResponse;
import com.sheconomy.sheeconomy.Network.response.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChangeCurrencyApiInterface {

    @GET("change-currency/{code}")
//    Call<> sendChangeCurrency(@Path("India") String country);
    Call<CurrencyResponse> getCurrency(@Path("code") String buyerCountry);
}
