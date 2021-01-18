package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Models.AuctionProduct;
import com.sheconomy.sheeconomy.Models.Banner;
import com.sheconomy.sheeconomy.Models.Brand;
import com.sheconomy.sheeconomy.Models.Category;
import com.sheconomy.sheeconomy.Models.FlashDeal;
import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.SliderImage;
import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;
import com.sheconomy.sheeconomy.Network.response.AuctionBidResponse;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.HomeView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.AppSettingsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.AuctionBidInteractor;
import com.sheconomy.sheeconomy.domain.interactors.AuctionProductInteractor;
import com.sheconomy.sheeconomy.domain.interactors.BannerInteractor;
import com.sheconomy.sheeconomy.domain.interactors.BestSellingInteractor;
import com.sheconomy.sheeconomy.domain.interactors.BrandInteractor;
import com.sheconomy.sheeconomy.domain.interactors.FeaturedProductInteractor;
import com.sheconomy.sheeconomy.domain.interactors.FlashDealInteractor;
import com.sheconomy.sheeconomy.domain.interactors.HomeCategoriesInteractor;
import com.sheconomy.sheeconomy.domain.interactors.SliderInteractor;
import com.sheconomy.sheeconomy.domain.interactors.TodaysDealInteractor;
import com.sheconomy.sheeconomy.domain.interactors.TopCategoryInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.AppSettingsInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.AuctionBidInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.AuctionProductInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.BannerInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.BestSellingInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.BrandInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.FeaturedProductInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.FlashDealInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.HomeCategoriesInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.SliderInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.TodaysDealInteractorImpl;
import com.sheconomy.sheeconomy.domain.interactors.impl.TopCategoriesInteractorImpl;
import com.google.gson.JsonObject;

import java.util.List;

public class HomePresenter extends AbstractPresenter implements AppSettingsInteractor.CallBack, SliderInteractor.CallBack, HomeCategoriesInteractor.CallBack, TodaysDealInteractor.CallBack, FlashDealInteractor.CallBack, BestSellingInteractor.CallBack, BannerInteractor.CallBack, FeaturedProductInteractor.CallBack, BrandInteractor.CallBack, TopCategoryInteractor.CallBack, AuctionProductInteractor.CallBack, AuctionBidInteractor.CallBack {
    private HomeView homeView;

    public HomePresenter(Executor executor, MainThread mainThread, HomeView homeView) {
        super(executor, mainThread);
        this.homeView = homeView;
    }

    public void getAppSettings(){
        new AppSettingsInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getSliderImages() {
        new SliderInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getHomeCategories() {
        new HomeCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTodaysDeal() {
        new TodaysDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFlashDeal() {
        new FlashDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBestSelling() {
        new BestSellingInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBanners() {
        new BannerInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFeaturedProducts() {
        new FeaturedProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTopCategories() {
        new TopCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getPopularbrands() {
        new BrandInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getAuctionProducts() {
        new AuctionProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void submitBid(JsonObject jsonObject, String token){
        new AuctionBidInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        if (homeView != null){
            homeView.onAppSettingsLoaded(appSettingsResponse);
        }
    }

    @Override
    public void onAppSettingsLoadedError() {

    }

    @Override
    public void onSliderDownloaded(List<SliderImage> sliderImages) {
        if (homeView != null) {
            homeView.setSliderImages(sliderImages);
        }
    }

    @Override
    public void onSliderDownloadError() {

    }

    @Override
    public void onHomeCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setHomeCategories(categories);
        }
    }

    @Override
    public void onHomeCategoriesDownloadError() {

    }

    @Override
    public void onTodaysDealProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setTodaysDeal(products);
        }
    }

    @Override
    public void onTodaysDealProductDownloadError() {

    }

    @Override
    public void onBestSellingProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setBestSelling(products);
        }
    }

    @Override
    public void onBestSellingProductDownloadError() {

    }

    @Override
    public void onFeaturedProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setFeaturedProducts(products);
        }
    }

    @Override
    public void onFeaturedProductDownloadError() {

    }

    @Override
    public void onTopCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setTopCategories(categories);
        }
    }

    @Override
    public void onTopCategoriesDownloadError() {

    }

    @Override
    public void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts) {
        if (homeView != null) {
            homeView.setAuctionProducts(auctionProducts);
        }
    }

    @Override
    public void onAuctionProductDownloadError() {

    }

    @Override
    public void onBannersDownloaded(List<Banner> banners) {
        if (homeView != null){
            homeView.setBanners(banners);
        }
    }

    @Override
    public void onBannersDownloadError() {

    }

    @Override
    public void onBrandsDownloaded(List<Brand> brands) {
        if (homeView != null){
            homeView.setPopularBrands(brands);
        }
    }

    @Override
    public void onBrandsDownloadError() {

    }

    @Override
    public void onBidSubmitted(AuctionBidResponse auctionBidResponse) {
        if (homeView != null){
            homeView.onAuctionBidSubmitted(auctionBidResponse);
        }
    }

    @Override
    public void onBidSubmittedError() {

    }

    @Override
    public void onFlashDealProductDownloaded(FlashDeal flashDeal) {
        if (homeView != null) {
            homeView.setFlashDeal(flashDeal);
        }
    }

    @Override
    public void onFlashDealProductDownloadError() {

    }
}