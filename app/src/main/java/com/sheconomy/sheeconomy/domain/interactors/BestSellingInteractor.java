package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Product;

import java.util.List;

public interface BestSellingInteractor {
    interface CallBack {

        void onBestSellingProductDownloaded(List<Product> products);

        void onBestSellingProductDownloadError();
    }
}
