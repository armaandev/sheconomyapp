package com.sheconomy.sheeconomy.Network.services;

import com.sheconomy.sheeconomy.Network.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllCategoryApiInterface {
    @GET("categories")
    Call<CategoryResponse> getAllCategories();
}
