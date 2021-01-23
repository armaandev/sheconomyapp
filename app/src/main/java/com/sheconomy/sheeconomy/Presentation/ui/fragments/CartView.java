package com.sheconomy.sheeconomy.Presentation.ui.fragments;

import com.sheconomy.sheeconomy.Models.CartModel;
import com.sheconomy.sheeconomy.Network.response.MessageResponse;
import com.sheconomy.sheeconomy.Network.response.RemoveCartResponse;

import java.util.List;

public interface CartView {
    void setCartItems(List<CartModel> cartItems);
    void showRemoveCartMessage(RemoveCartResponse removeCartResponse);
    void showCartQuantityUpdateMessage(MessageResponse messageResponse);
}
