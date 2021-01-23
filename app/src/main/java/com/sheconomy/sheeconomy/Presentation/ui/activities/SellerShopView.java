package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.Shop;

import java.util.List;

public interface SellerShopView {
    void onShopDetailsLoaded(Shop shop);
    void setFeaturedProducts(List<Product> products);
    void setTopSellingProducts(List<Product> products);
    void setNewProducts(List<Product> products);
}
