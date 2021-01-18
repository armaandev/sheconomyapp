package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.UserBid;

import java.util.List;

public interface UserBidInteractor {
    interface CallBack {

        void onUserBidLodaded(List<UserBid> userBids);

        void onUserBidError();
    }
}
