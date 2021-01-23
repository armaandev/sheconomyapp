package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Category;

import java.util.List;

public interface AllCategoryInteractor {
    interface CallBack {

        void onAllCategoriesDownloaded(List<Category> categories);

        void onAllCategoriesDownloadError();
    }
}
