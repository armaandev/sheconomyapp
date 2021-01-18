package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Models.ShippingAddress;
import com.sheconomy.sheeconomy.Models.User;
import com.sheconomy.sheeconomy.Network.response.ProfileInfoUpdateResponse;
import com.sheconomy.sheeconomy.Network.response.ShippingInfoResponse;
import com.sheconomy.sheeconomy.Presentation.ui.activities.AccountInfoView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.ProfileInfoUpdateInteractor;
import com.sheconomy.sheeconomy.domain.interactors.ShippingInfoDeleteInteractor;
import com.sheconomy.sheeconomy.domain.interactors.ShippingInfoInteractor;
import com.sheconomy.sheeconomy.domain.interactors.ShippingInfoCreateInteractor;
import com.sheconomy.sheeconomy.domain.interactors.UserInfoInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.ProfileInfoUpdateInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.ShippingInfoDeleteInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.ShippingInfoInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.ShippingInfoCreateInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.UserInfoInteractorImpl;
import com.google.gson.JsonObject;

import java.util.List;

public class AccountInfoPresenter extends AbstractPresenter implements ProfileInfoUpdateInteractor.CallBack, ShippingInfoCreateInteractor.CallBack, UserInfoInteractor.CallBack, ShippingInfoInteractor.CallBack, ShippingInfoDeleteInteractor.CallBack {
    private AccountInfoView accountInfoView;

    public AccountInfoPresenter(Executor executor, MainThread mainThread, AccountInfoView accountInfoView) {
        super(executor, mainThread);
        this.accountInfoView = accountInfoView;
    }

    public void sendUpdateProfileRequest(JsonObject jsonObject, String token) {
        new ProfileInfoUpdateInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    public void getShippingAddresses(int user_id, String token) {
        new ShippingInfoInteractorImpl(mExecutor, mMainThread, this, user_id, token).execute();
    }

    public void addNewShippingRequest(JsonObject jsonObject, String token) {
        new ShippingInfoCreateInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    public void deleteShippingAddress(int address_id, String token) {
        new ShippingInfoDeleteInteractorImpl(mExecutor, mMainThread, this, address_id, token).execute();
    }

    public void getUpdatedUserInfo(int user_id, String token) {
        new UserInfoInteractorImpl(mExecutor, mMainThread, this, user_id, token).execute();
    }

    @Override
    public void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse) {
        if (accountInfoView != null){
            accountInfoView.onProfileInfoUpdated(profileInfoUpdateResponse);
        }
    }

    @Override
    public void onProfileInfoUpdatedError() {

    }

    @Override
    public void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse) {
        if (accountInfoView != null){
            accountInfoView.onShippingInfoCreated(shippingInfoResponse);
        }
    }

    @Override
    public void onShippingInfoCreateError() {

    }

    @Override
    public void onUserInfoLodaded(User user) {
        if (accountInfoView != null){
            accountInfoView.setUpdatedUserInfo(user);
        }
    }

    @Override
    public void onUserInfoError() {

    }

    @Override
    public void onShippingInfoRetrived(List<ShippingAddress> shippingAddresses) {
        if (accountInfoView != null){
            accountInfoView.setShippingAddresses(shippingAddresses);
        }
    }

    @Override
    public void onShippingInfoRetrivedError() {

    }

    @Override
    public void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse) {
        if (accountInfoView != null){
            accountInfoView.onShippingInfoDeleted(shippingInfoResponse);
        }
    }

    @Override
    public void onShippingInfoDeleteError() {

    }
}
