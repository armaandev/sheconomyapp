package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.OrderDetail;

import java.util.List;

public interface PurchaseHistoryDetailsView {
    void onOrderDetailsLoaded(List<OrderDetail> orderDetailList);
}
