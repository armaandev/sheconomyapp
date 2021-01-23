package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Network.response.AuthResponse;
import com.sheconomy.sheeconomy.Presentation.ui.activities.LoginView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.LoginInteractor;
import com.sheconomy.sheeconomy.domain.interactors.SocialLoginInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.LoginInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.SocialLoginInteractorImpl;

public class LoginPresenter extends AbstractPresenter implements LoginInteractor.CallBack, SocialLoginInteractor.CallBack {

    private LoginView loginView;

    public LoginPresenter(Executor executor, MainThread mainThread, LoginView loginView) {
        super(executor, mainThread);
        this.loginView = loginView;
    }

    public void validLogin(String email, String password) {
        new LoginInteractorImpl(mExecutor, mMainThread, this, email, password).execute();
    }

    public void validSocialLogin(String id, String name, String email) {
        new SocialLoginInteractorImpl(mExecutor, mMainThread, this, id, name, email).execute();
    }

    @Override
    public void onValidLogin(AuthResponse authResponse) {
        if (loginView != null){
            loginView.setLoginResponse(authResponse);
        }
    }

    @Override
    public void onLoginError() {
        if (loginView != null){
            loginView.showErrorResponse();
        }
    }
}
