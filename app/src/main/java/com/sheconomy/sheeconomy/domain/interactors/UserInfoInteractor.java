package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.User;

public interface UserInfoInteractor {
    interface CallBack {

        void onUserInfoLodaded(User user);

        void onUserInfoError();
    }
}
