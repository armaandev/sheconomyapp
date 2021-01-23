package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Network.response.RegistrationResponse;
import com.sheconomy.sheeconomy.Presentation.ui.activities.RegisterView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.RegisterInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.RegisterInteractorImpl;
import com.google.gson.JsonObject;

public class RegisterPresenter extends AbstractPresenter implements RegisterInteractor.CallBack {

    private RegisterView registerView;

    public RegisterPresenter(Executor executor, MainThread mainThread, RegisterView registerView) {
        super(executor, mainThread);
        this.registerView = registerView;
    }

    public void validateRegistration(JsonObject jsonObject) {
        new RegisterInteractorImpl(mExecutor, mMainThread, this, jsonObject).execute();
    }

    @Override
    public void onRegistrationDone(RegistrationResponse registrationResponse) {
        if (registerView != null){
            registerView.setRegistrationResponse(registrationResponse);
        }
    }

    @Override
    public void onRegistrationError() {

    }
}
