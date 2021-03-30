package com.sheconomy.sheeconomy.Presentation.presenters;

import android.util.Log;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Presentation.ui.activities.SellerPaymentsView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.SellerPaymentsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.SellerPaymentsInteratorImp;
import java.util.List;

public class SellerPaymentsPresenter extends AbstractPresenter implements SellerPaymentsInteractor.CallBack {


    private SellerPaymentsView sellerPaymentsView;
//    private int type=0;

    public SellerPaymentsPresenter(Executor executor, MainThread mainThread, SellerPaymentsView sellerPaymentsView) {
        super(executor, mainThread);
        this.sellerPaymentsView = sellerPaymentsView;
    }
//    public void getSellerPayment(String url){
//    new SellerPaymentsInteratorImp(mExecutor, mMainThread, this, url).execute();
//     }

    public void getSellerPayment(int seller_id) {
        new SellerPaymentsInteratorImp(mExecutor, mMainThread, this,seller_id).execute();
    }

    @Override
    public void onSellerPaymentsLoaded(List<SellerPayments> sellerPayments) {
        Log.d("onSellerPaymentsLoades",sellerPayments.toString());
        if (sellerPaymentsView != null){
            sellerPaymentsView.onSellerDetailsLoaded(sellerPayments);
        }
    }

    @Override
    public void onSellerPaymentsLoadError() {

    }


//    @Override
//    public void onSellerPaymentsLoaded(SellerPayments sellerPayments) {
//
//        if (sellerPaymentsView != null){
//            sellerPaymentsView.onSellerDetailsLoaded(sellerPayments);
//        }
//
//    }
//
//    @Override
//    public void onSellerPaymentsLoadError() {
//
//    }
}
