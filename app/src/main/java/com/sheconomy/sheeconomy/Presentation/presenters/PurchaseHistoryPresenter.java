package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Models.PurchaseHistory;
import com.sheconomy.sheeconomy.Presentation.ui.activities.PurchaseHistoryView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.PurchaseHistoryInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.PurchaseHistoryInteractorImpl;

import java.util.List;

public class PurchaseHistoryPresenter extends AbstractPresenter implements PurchaseHistoryInteractor.CallBack {
    private PurchaseHistoryView purchaseHistoryView;

    public PurchaseHistoryPresenter(Executor executor, MainThread mainThread, PurchaseHistoryView purchaseHistoryView) {
        super(executor, mainThread);
        this.purchaseHistoryView = purchaseHistoryView;
    }

    public void getPurchaseHistoryItems(int id, String token) {
        new PurchaseHistoryInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    @Override
    public void onPurchaseHistoryLodaded(List<PurchaseHistory> purchaseHistories) {
        if (purchaseHistoryView != null){
            purchaseHistoryView.onPurchaseHistoryLoaded(purchaseHistories);
        }
    }

    @Override
    public void onPurchaseHistoryLodadedError() {

    }
}
