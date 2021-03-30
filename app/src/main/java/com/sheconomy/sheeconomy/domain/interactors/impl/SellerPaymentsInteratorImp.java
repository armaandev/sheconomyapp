package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.SellerPaymentResponse;
import com.sheconomy.sheeconomy.Network.response.ShopResponse;
import com.sheconomy.sheeconomy.Network.services.SellerPaymentApiInterface;
import com.sheconomy.sheeconomy.Network.services.ShopApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.SellerPaymentsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.ShopInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerPaymentsInteratorImp extends AbstractInteractor {
    private SellerPaymentsInteractor.CallBack mCallback;
    private SellerPaymentApiInterface apiService;
    private int seller_id;


    public SellerPaymentsInteratorImp(Executor threadExecutor, MainThread mainThread, SellerPaymentsInteractor.CallBack mCallback,int seller_id) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.seller_id = seller_id;

    }

    @Override
    public void run() {

        apiService = ApiClient.getClient().create(SellerPaymentApiInterface.class);
         Call<SellerPaymentResponse> call = apiService.getSellerPayment(seller_id);

        call.enqueue(new Callback<SellerPaymentResponse>() {
            @Override
            public void onResponse(Call <SellerPaymentResponse> call, Response<SellerPaymentResponse> response) {
                try {
                    if(response.isSuccessful()){
                        Log.d("Hello",response.toString());

                    }
//                    Log.d("Test", String.valueOf(response.body().getSuccess()));
//                    mCallback.onSellerPaymentsLoaded(response.body().getData().get(0));
                    mCallback.onSellerPaymentsLoaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<SellerPaymentResponse> call, Throwable t) {
                mCallback.onSellerPaymentsLoadError();
            }
        });
    }
}

