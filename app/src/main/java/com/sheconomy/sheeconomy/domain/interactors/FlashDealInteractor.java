package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.FlashDeal;

public interface FlashDealInteractor {
    interface CallBack {

        void onFlashDealProductDownloaded(FlashDeal flashDeal);

        void onFlashDealProductDownloadError();
    }
}