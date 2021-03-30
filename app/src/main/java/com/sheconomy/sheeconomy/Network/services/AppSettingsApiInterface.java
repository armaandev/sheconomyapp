package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppSettingsApiInterface {
    @GET("settings")
    Call<AppSettingsResponse> getAppSettings();
}


