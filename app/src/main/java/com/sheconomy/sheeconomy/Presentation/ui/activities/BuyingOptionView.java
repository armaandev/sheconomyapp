package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Network.response.AddToCartResponse;
import com.sheconomy.sheeconomy.Network.response.VariantResponse;

public interface BuyingOptionView {
    void setVariantprice(VariantResponse variantResponse);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
}
