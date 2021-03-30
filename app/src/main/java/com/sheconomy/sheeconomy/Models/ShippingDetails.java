package com.sheconomy.sheeconomy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShippingDetails implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("shipping_type")
    @Expose
    private String shippingType;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("rate_weight")
    @Expose
    private String rateWeight;
//    private final static long serialVersionUID = 1488774439617075473L;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRateWeight() {
        return rateWeight;
    }

    public void setRateWeight(String rateWeight) {
        this.rateWeight = rateWeight;
    }

}
