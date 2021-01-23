package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.ProductSearchResponse;
import com.sheconomy.sheeconomy.Network.services.ProductSearchApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.ProductSearchInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSearchInteractorImpl extends AbstractInteractor {
    private ProductSearchInteractor.CallBack mCallback;
    private ProductSearchApiInterface apiService;
    private String url;

    public ProductSearchInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductSearchInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductSearchApiInterface.class);
        Call<ProductSearchResponse> call = apiService.getSearchedProducts(url);

        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                try {
                    mCallback.onProductSearched(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
                mCallback.onProductSearchedError();
            }
        });

    }
}
