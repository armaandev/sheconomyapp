package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryView {
    void onPurchaseHistoryLoaded(List<PurchaseHistory> purchaseHistoryList);
}
