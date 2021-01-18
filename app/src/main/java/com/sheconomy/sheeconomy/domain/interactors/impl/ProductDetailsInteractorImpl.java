package com.sheconomy.sheeconomy.domain.interactors.impl;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.ProductDetialsResponse;
import com.sheconomy.sheeconomy.Network.services.ProductDetailsApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.ProductDetailsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsInteractorImpl extends AbstractInteractor {
    private ProductDetailsInteractor.CallBack mCallback;
    private ProductDetailsApiInterface apiService;
    private String url;

    public ProductDetailsInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductDetailsInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductDetailsApiInterface.class);
        Call<ProductDetialsResponse> call = apiService.getProductDetails(url);

        call.enqueue(new Callback<ProductDetialsResponse>() {
            @Override
            public void onResponse(Call<ProductDetialsResponse> call, Response<ProductDetialsResponse> response) {
                try {
                    //Log.d("Mehedi", response.toString());
                    mCallback.onProductDetailsDownloaded(response.body().getData().get(0));
                } catch (Exception e) {
                    //Log.d("Mehedi", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductDetialsResponse> call, Throwable t) {
                //Log.d("Mehedi", t.getLocalizedMessage());
                mCallback.onProductDetailsDownloadError();
            }
        });

    }
}
