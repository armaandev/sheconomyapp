package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.PolicyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PolicyApiInterface {
    @GET
    Call<PolicyResponse> getPolicy(@Url String url);
}
