package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Network.response.StripeClientSecretResponse;

public interface RazorPayInteractor {
    interface CallBack {

        void ononClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);

        void ononClientSecretReceiveError();
    }

}
