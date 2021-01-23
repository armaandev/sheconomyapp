package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.ShippingAddress;
import com.sheconomy.sheeconomy.Models.User;
import com.sheconomy.sheeconomy.Network.response.ProfileInfoUpdateResponse;
import com.sheconomy.sheeconomy.Network.response.ShippingInfoResponse;

import java.util.List;

public interface AccountInfoView {
    void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse);
    void setShippingAddresses(List<ShippingAddress> shippingAddresses);
    void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse);
    void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse);
    void setUpdatedUserInfo(User user);
}
