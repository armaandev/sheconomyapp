package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Models.SellerPayments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SellerPaymentsApiInterface {
    @GET("seller-payment/{id}")
    Call<List<SellerPayments>> getSellerPayments(@Path("id") int sellerId);
}
