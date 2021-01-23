package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.ProductDetails;

public interface ProductDetailsInteractor {
    interface CallBack {

        void onProductDetailsDownloaded(ProductDetails productDetails);

        void onProductDetailsDownloadError();
    }
}
