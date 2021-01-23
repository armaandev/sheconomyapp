package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Product;

import java.util.List;

public interface ProductInteractor {
    interface CallBack {

        void onProductDownloaded(List<Product> products);

        void onProductDownloadError();
    }
}
