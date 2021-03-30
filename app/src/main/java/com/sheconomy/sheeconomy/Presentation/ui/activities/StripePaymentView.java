package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Network.response.StripeClientSecretResponse;

public interface StripePaymentView {

    void onClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);
}
