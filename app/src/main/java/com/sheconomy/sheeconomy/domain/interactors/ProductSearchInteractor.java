package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.ProductSearchResponse;

public interface ProductSearchInteractor {
    interface CallBack {

        void onProductSearched(ProductSearchResponse productSearchResponse);

        void onProductSearchedError();
    }
}
