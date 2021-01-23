package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.SliderImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SliderImageApiInterface {
    @GET("sliders")
    Call<SliderImageResponse> getSliderImages();
}
