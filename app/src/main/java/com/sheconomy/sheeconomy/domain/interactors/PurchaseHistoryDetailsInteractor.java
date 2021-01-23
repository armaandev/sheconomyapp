package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.OrderDetail;

import java.util.List;

public interface PurchaseHistoryDetailsInteractor {
    interface CallBack {

        void onOrderDetailsLoaded(List<OrderDetail> orderDetails);

        void onOrderDetailsLoadError();
    }
}
