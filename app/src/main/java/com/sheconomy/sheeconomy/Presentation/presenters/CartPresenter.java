package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Models.CartModel;
import com.sheconomy.sheeconomy.Network.response.MessageResponse;
import com.sheconomy.sheeconomy.Network.response.RemoveCartResponse;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.CartView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.CartInteractor;
import com.sheconomy.sheeconomy.domain.interactors.CartQuantityInteractor;
import com.sheconomy.sheeconomy.domain.interactors.RemoveCartInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.CartInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.CartQuantityInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.RemoveCartInteractorImpl;

import java.util.List;

public class CartPresenter extends AbstractPresenter implements CartInteractor.CallBack, RemoveCartInteractor.CallBack, CartQuantityInteractor.CallBack {
    private CartView cartView;

    public CartPresenter(Executor executor, MainThread mainThread, CartView cartView) {
        super(executor, mainThread);
        this.cartView = cartView;
    }

    public void getCartItems(int id, String token) {
        new CartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void removeCartItem(int id, String token){
        new RemoveCartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void updateCartQuantity(int id, int quantity, String token) {
        new CartQuantityInteractorImpl(mExecutor, mMainThread, this, id, quantity, token).execute();
    }

    @Override
    public void onCartLodaded(List<CartModel> cartModels) {
        if(cartView != null){
            cartView.setCartItems(cartModels);
        }
    }

    @Override
    public void onCartError() {

    }

    @Override
    public void onCartItemRemoved(RemoveCartResponse removeCartResponse) {
        if(cartView != null){
            cartView.showRemoveCartMessage(removeCartResponse);
        }
    }

    @Override
    public void onCartItemRemovedError() {

    }

    @Override
    public void onCartQuantityUpdated(MessageResponse messageResponse) {
        if(cartView != null){
            cartView.showCartQuantityUpdateMessage(messageResponse);
        }
    }

    @Override
    public void onCartQuantityUpdatedError() {

    }
}
