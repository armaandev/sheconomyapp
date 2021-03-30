package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.ChangeCurrency;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Network.response.AuthResponse;

import java.util.List;

public interface ChangeCurrencyInteractor {
    interface CallBack {

        void onChangeCurrenciesLoaded(List<ChangeCurrency> changeCurrencies);

        void onChangeCurrenciesLoadedError();
    }
}
