package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.Review;

import java.util.List;

public interface ReviewInteractor {
    interface CallBack {

        void onReviewLodaded(List<Review> reviews);

        void onReviewError();
    }
}
