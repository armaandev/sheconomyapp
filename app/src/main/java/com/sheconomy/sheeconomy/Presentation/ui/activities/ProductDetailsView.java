package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.ProductDetails;
import com.sheconomy.sheeconomy.Network.response.AddToCartResponse;
import com.sheconomy.sheeconomy.Network.response.AddToWishlistResponse;
import com.sheconomy.sheeconomy.Network.response.CheckWishlistResponse;
import com.sheconomy.sheeconomy.Network.response.RemoveWishlistResponse;

import java.util.List;

public interface ProductDetailsView {
    void setProductDetails(ProductDetails productDetails);
    void setRelatedProducts(List<Product> relatedProducts);
    void setTopSellingProducts(List<Product> topSellingProducts);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
    void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage);
    void onCheckWishlist(CheckWishlistResponse checkWishlistResponse);
    void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse);
}
