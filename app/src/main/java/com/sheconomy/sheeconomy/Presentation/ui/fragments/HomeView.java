package com.sheconomy.sheeconomy.Presentation.ui.fragments;

import com.sheconomy.sheeconomy.Models.AuctionProduct;
import com.sheconomy.sheeconomy.Models.Banner;
import com.sheconomy.sheeconomy.Models.Brand;
import com.sheconomy.sheeconomy.Models.Category;
import com.sheconomy.sheeconomy.Models.FlashDeal;
import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.SliderImage;
import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;
import com.sheconomy.sheeconomy.Network.response.AuctionBidResponse;

import java.util.List;

public interface HomeView {
    void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

    void setSliderImages(List<SliderImage> sliderImages);

    void setHomeCategories(List<Category> categories);

    void setTodaysDeal(List<Product> products);

    void setFlashDeal(FlashDeal flashDeal);

    void setBanners(List<Banner> banners);

    void setBestSelling(List<Product> products);

    void setFeaturedProducts(List<Product> products);

    void setTopCategories(List<Category> categories);

    void setPopularBrands(List<Brand> brands);

    void setAuctionProducts(List<AuctionProduct> auctionProducts);

    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}