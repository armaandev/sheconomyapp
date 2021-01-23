package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.SliderImage;

import java.util.List;

public interface SliderInteractor {

    interface CallBack {

        void onSliderDownloaded(List<SliderImage> sliderImages);

        void onSliderDownloadError();
    }
}
