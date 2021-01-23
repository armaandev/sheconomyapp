package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandApiInterface {
    @GET("brands")
    Call<BrandResponse> getBrands();
}
