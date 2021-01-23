package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Models.UserBid;
import com.sheconomy.sheeconomy.Network.response.AuctionBidResponse;

import java.util.List;

public interface MybidView {
    void setUserBids(List<UserBid> userBids);
    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}
