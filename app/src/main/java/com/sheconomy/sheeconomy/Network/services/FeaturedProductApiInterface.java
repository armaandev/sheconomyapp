package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeaturedProductApiInterface {
    @GET("products/featured")
    Call<ProductResponse> getFeaturedPrdocuts();
}
