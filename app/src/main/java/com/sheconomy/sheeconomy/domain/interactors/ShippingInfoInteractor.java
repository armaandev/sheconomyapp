package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.ShippingAddress;
import java.util.List;

public interface ShippingInfoInteractor {
    interface CallBack {

        void onShippingInfoRetrived(List<ShippingAddress> shippingAddresses);

        void onShippingInfoRetrivedError();
    }
}
