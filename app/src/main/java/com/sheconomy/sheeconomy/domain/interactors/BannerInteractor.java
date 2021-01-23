package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Banner;

import java.util.List;

public interface BannerInteractor {
    interface CallBack {

        void onBannersDownloaded(List<Banner> banners);

        void onBannersDownloadError();
    }
}
