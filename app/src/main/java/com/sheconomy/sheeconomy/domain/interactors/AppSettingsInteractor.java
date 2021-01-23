package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;

public interface AppSettingsInteractor {
    interface CallBack {

        void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

        void onAppSettingsLoadedError();
    }
}
