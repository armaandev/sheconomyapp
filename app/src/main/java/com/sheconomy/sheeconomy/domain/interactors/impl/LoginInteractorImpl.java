package com.sheconomy.sheeconomy.domain.interactors.impl;

import android.util.Log;

import com.sheconomy.sheeconomy.Network.ApiClient;
import com.sheconomy.sheeconomy.Network.response.AuthResponse;
import com.sheconomy.sheeconomy.Network.services.LoginApiInterface;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.LoginInteractor;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl extends AbstractInteractor {
    private LoginInteractor.CallBack mCallback;
    private LoginApiInterface apiService;
    private String email;
    private String password;


    public LoginInteractorImpl(Executor threadExecutor, MainThread mainThread, LoginInteractor.CallBack callBack, String email, String password) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(LoginApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("remember_me", true);

        Call<AuthResponse> call = apiService.sendLoginCredentials(jsonObject);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    //Log.d("Test", response.body().toString());
                    mCallback.onValidLogin(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                    mCallback.onLoginError();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.d("Test", String.valueOf(t.getMessage()));
                mCallback.onLoginError();
            }
        });

    }
}
