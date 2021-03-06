package com.sheconomy.sheeconomy.Presentation.presenters;

import android.util.Log;

import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Network.response.CouponResponse;
import com.sheconomy.sheeconomy.Network.response.OrderResponse;
import com.sheconomy.sheeconomy.Network.response.StripeClientSecretResponse;
import com.sheconomy.sheeconomy.Presentation.ui.activities.PaymentView;
import com.sheconomy.sheeconomy.Presentation.ui.activities.RazorPayView;
import com.sheconomy.sheeconomy.Presentation.ui.activities.StripePaymentView;
import com.sheconomy.sheeconomy.Presentation.ui.activities.impl.RazorPayActivity;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.CODInteractor;
import com.sheconomy.sheeconomy.domain.interactors.CouponInteractor;
import com.sheconomy.sheeconomy.domain.interactors.OrderInteractor;
import com.sheconomy.sheeconomy.domain.interactors.PaypalInteractor;
import com.sheconomy.sheeconomy.domain.interactors.RazorPayInteractor;
import com.sheconomy.sheeconomy.domain.interactors.SellerPaymentsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.StripeInteractor;
import com.sheconomy.sheeconomy.domain.interactors.WalletInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.CODInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.CouponInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.OrderInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.PaypalInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.SellerPaymentsInteratorImp;
import com.sheconomy.sheeconomy.domain.interactors.impl.StripeInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.WalletInteractorImpl;
import com.google.gson.JsonObject;

import java.util.List;

public class PaymentPresenter extends AbstractPresenter implements CouponInteractor.CallBack, PaypalInteractor.CallBack, StripeInteractor.CallBack, CODInteractor.CallBack, OrderInteractor.CallBack, WalletInteractor.CallBack,RazorPayInteractor.CallBack {
    private PaymentView paymentView;
    private StripePaymentView stripePaymentView;
    private RazorPayView razorPayView;
    //SellerPaymentsInteractor.CallBack

    public PaymentPresenter(Executor executor, MainThread mainThread, PaymentView paymentView) {
        super(executor, mainThread);
        this.paymentView = paymentView;
    }

    public PaymentPresenter(Executor executor, MainThread mainThread, StripePaymentView stripePaymentView) {
        super(executor, mainThread);
        this.stripePaymentView = stripePaymentView;
    }
    //new line for razorPay

    public PaymentPresenter(Executor executor, MainThread mainThread, RazorPayView razorPayView) {
        super(executor, mainThread);
        this.razorPayView = razorPayView;
    }


    public void applyCoupon(int user_id, String code, String token) {
        new CouponInteractorImpl(mExecutor, mMainThread, this, user_id, code, token).execute();
    }

    public void submitPaypalOrder(String token, JsonObject jsonObject) {
        new PaypalInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitStripeRequest(String token, JsonObject jsonObject) {
        new StripeInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }
    //new presenter for razorpay
//    public void submitRazorPayRequest(String token, JsonObject jsonObject) {
//        new RazorPayInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
//    }

     //new presenter for sellerPayments

//    public void getSellerPayment() {
//        new SellerPaymentsInteratorImp(mExecutor, mMainThread, this).execute();
//    }

    public void submitWalletOrder(String token, JsonObject jsonObject) {
        new WalletInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitCODOrder(String token, JsonObject jsonObject) {
        new CODInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitOrder(String token, JsonObject jsonObject) {
        new OrderInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    @Override
    public void onCouponApplied(CouponResponse couponResponse) {
        if (paymentView != null){
            paymentView.onCouponApplied(couponResponse);
        }
    }

    @Override
    public void onCouponAppliedError() {

    }

    @Override
    public void onPayaplOrderSubmitted(OrderResponse orderResponse) {
        Log.d("Khansb",orderResponse.toString());
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onPayaplOrderSubmitError() {

    }

    @Override
    public void ononClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse) {
        Log.d("Chamar",stripeClientSecretResponse.toString());
        if (stripePaymentView != null){
            stripePaymentView.onClientSecretReceived(stripeClientSecretResponse);
        }
    }

    @Override
    public void ononClientSecretReceiveError() {

    }

    @Override
    public void onCODOrderSubmitted(OrderResponse orderResponse) {
        Log.d("Aliyaaa",orderResponse.toString());
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onCODOrderSubmitError() {

    }

    @Override
    public void onOrderSubmitted(OrderResponse orderResponse) {
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onOrderSubmitError() {

    }

    @Override
    public void onWalletOrderSubmitted(OrderResponse orderResponse) {
//        Log.d("Armaan",orderResponse.toString());
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onWalletOrderSubmitError() {

    }


//new line added
//    @Override
//    public void onSellerPaymentsLoaded(List<SellerPayments> sellerPayments) {
//        Log.d("SellerPayments",sellerPayments.toString());
//        if (paymentView != null) {
//            paymentView.onSellerDetailsLoaded(sellerPayments);
//        }
//    }
//
//    @Override
//    public void onSellerPaymentsLoadError() {
//
//    }


//    @Override
//    public void onRazorPayOrderSubmitted(OrderResponse orderResponse) {
//        if (paymentView != null){
//            paymentView.onOrderSubmitted(orderResponse);
//        }
//    }
//
//    @Override
//    public void onRazorPayOrderSubmitError() {
//
//    }
}
