package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Category;

import java.util.List;

public interface TopCategoryInteractor {
    interface CallBack {

        void onTopCategoriesDownloaded(List<Category> categories);

        void onTopCategoriesDownloadError();
    }
}
