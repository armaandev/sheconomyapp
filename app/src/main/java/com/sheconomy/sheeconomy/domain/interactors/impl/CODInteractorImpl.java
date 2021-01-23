package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.OrderResponse;
import com.sheconomy.sheeconomy.Network.services.CODApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.CODInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CODInteractorImpl extends AbstractInteractor {
    private CODInteractor.CallBack mCallback;
    private CODApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;

    public CODInteractorImpl(Executor threadExecutor, MainThread mainThread, CODInteractor.CallBack callBack, String auth_token, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.auth_token = "Bearer "+auth_token;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(CODApiInterface.class);

        Call<OrderResponse> call = apiService.sendPlaceOrderRequest(auth_token, jsonObject);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onCODOrderSubmitted(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onCODOrderSubmitError();
            }
        });

    }
}
