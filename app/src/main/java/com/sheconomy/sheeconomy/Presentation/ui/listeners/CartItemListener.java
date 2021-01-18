package com.sheconomy.sheeconomy.Presentation.ui.listeners;

import com.sheconomy.sheeconomy.Models.CartModel;

public interface CartItemListener {
    void onCartRemove(CartModel cartModel);
    void onQuantityUpdate(int quantity, CartModel cartModel);
}
