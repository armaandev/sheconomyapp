package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.CouponResponse;

public interface CouponInteractor {
    interface CallBack {

        void onCouponApplied(CouponResponse couponResponse);

        void onCouponAppliedError();
    }
}
