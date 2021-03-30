package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Models.ChangeCurrency;
import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.CurrencyResponse;
import com.sheconomy.sheeconomy.Network.response.SellerPaymentResponse;
import com.sheconomy.sheeconomy.Network.services.ChangeCurrencyApiInterface;
import com.sheconomy.sheeconomy.Network.services.SellerPaymentApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.ChangeCurrencyInteractor;
import com.sheconomy.sheeconomy.domain.interactors.SellerPaymentsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeCurrencyInteractorImp extends AbstractInteractor {

    private ChangeCurrencyInteractor.CallBack mCallback;
    private ChangeCurrencyApiInterface apiService;
    private String code;


    public ChangeCurrencyInteractorImp(Executor threadExecutor, MainThread mainThread,ChangeCurrencyInteractor.CallBack mCallback, String code) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.code = code;
    }

    @Override
    public void run() {

        apiService = ApiClient.getClient().create(ChangeCurrencyApiInterface.class);
        Call<CurrencyResponse> call = apiService.getCurrency("Rupee");

        call.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call <CurrencyResponse> call, Response<CurrencyResponse> response) {
                try {
                    if(response.isSuccessful()){
                        Log.d("Hello",response.toString());

                    }
//                    Log.d("Test", String.valueOf(response.body().getSuccess()));
//                    mCallback.onSellerPaymentsLoaded(response.body().getData().get(0));
                    mCallback.onChangeCurrenciesLoaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {

            }
        });
    }


}
