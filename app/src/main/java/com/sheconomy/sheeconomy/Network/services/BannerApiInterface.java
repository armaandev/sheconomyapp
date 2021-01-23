package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.BannerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerApiInterface {
    @GET("banners")
    Call<BannerResponse> getBanners();
}
