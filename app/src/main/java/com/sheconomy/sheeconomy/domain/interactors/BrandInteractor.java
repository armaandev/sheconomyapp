package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Brand;

import java.util.List;

public interface BrandInteractor {
    interface CallBack {

        void onBrandsDownloaded(List<Brand> brands);

        void onBrandsDownloadError();
    }
}
