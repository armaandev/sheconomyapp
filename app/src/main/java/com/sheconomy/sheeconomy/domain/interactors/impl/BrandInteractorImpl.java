package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.BrandResponse;
import com.sheconomy.sheeconomy.Network.services.BrandApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.BrandInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandInteractorImpl extends AbstractInteractor {
    private BrandInteractor.CallBack mCallback;
    private BrandApiInterface apiService;

    public BrandInteractorImpl(Executor threadExecutor, MainThread mainThread, BrandInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(BrandApiInterface.class);
        Call<BrandResponse> call = apiService.getBrands();

        call.enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                try {
                    mCallback.onBrandsDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                mCallback.onBrandsDownloadError();
            }
        });

    }
}
