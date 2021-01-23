package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.CartModel;

import java.util.List;

public interface CartInteractor {
    interface CallBack {

        void onCartLodaded(List<CartModel> cartModel);

        void onCartError();
    }
}
