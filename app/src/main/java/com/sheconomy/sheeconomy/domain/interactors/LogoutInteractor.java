package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.LogoutResponse;

public interface LogoutInteractor {
    interface CallBack {

        void onLoggedOut(LogoutResponse logoutResponse);

        void onLoggedOutError();
    }
}
