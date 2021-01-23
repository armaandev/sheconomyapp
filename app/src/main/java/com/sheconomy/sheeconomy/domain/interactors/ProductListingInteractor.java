package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.ProductListingResponse;

public interface ProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(ProductListingResponse productListingResponse);

        void onProductDownloadError();
    }
}
