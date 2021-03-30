package com.sheconomy.sheeconomy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerPayments  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("paypal_mid")
    @Expose
    private String paypalMid;
    @SerializedName("paypal_key")
    @Expose
    private String paypalKey;
    @SerializedName("paypal_email")
    @Expose
    private String paypalEmail;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("razorpay_key")
    @Expose
    private String razorpayKey;
    @SerializedName("razorpay_secret")
    @Expose
    private String razorpaySecret;
    @SerializedName("razorpay_status")
    @Expose
    private String razorpayStatus;
    @SerializedName("stripe_key")
    @Expose
    private String stripeKey;
    @SerializedName("stripe_secret")
    @Expose
    private String stripeSecret;
    @SerializedName("stripe_status")
    @Expose
    private String stripeStatus;
    @SerializedName("instamojo_key")
    @Expose
    private String instamojoKey;
    @SerializedName("instamojo_token")
    @Expose
    private String instamojoToken;
    @SerializedName("instamojo_status")
    @Expose
    private String instamojoStatus;

    //    private final static long serialVersionUID = 5239457542328792327L;

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

    public String getPaypalMid() {
        return paypalMid;
    }

    public void setPaypalMid(String paypalMid) {
        this.paypalMid = paypalMid;
    }

    public String getPaypalKey() {
        return paypalKey;
    }

    public void setPaypalKey(String paypalKey) {
        this.paypalKey = paypalKey;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public String getRazorpayKey() {
        return razorpayKey;
    }

    public void setRazorpayKey(String razorpayKey) {
        this.razorpayKey = razorpayKey;
    }

    public String getRazorpaySecret() {
        return razorpaySecret;
    }

    public void setRazorpaySecret(String razorpaySecret) {
        this.razorpaySecret = razorpaySecret;
    }

    public String getRazorpayStatus() {
        return razorpayStatus;
    }

    public void setRazorpayStatus(String razorpayStatus) {
        this.razorpayStatus = razorpayStatus;
    }

    public String getStripeKey() {
        return stripeKey;
    }

    public void setStripeKey(String stripeKey) {
        this.stripeKey = stripeKey;
    }

    public String getStripeSecret() {
        return stripeSecret;
    }

    public void setStripeSecret(String stripeSecret) {
        this.stripeSecret = stripeSecret;
    }

    public String getStripeStatus() {
        return stripeStatus;
    }

    public void setStripeStatus(String stripeStatus) {
        this.stripeStatus = stripeStatus;
    }

    public String getInstamojoKey() {
        return instamojoKey;
    }

    public void setInstamojoKey(String instamojoKey) {
        this.instamojoKey = instamojoKey;
    }

    public String getInstamojoToken() {
        return instamojoToken;
    }

    public void setInstamojoToken(String instamojoToken) {
        this.instamojoToken = instamojoToken;
    }

    public String getInstamojoStatus() {
        return instamojoStatus;
    }

    public void setInstamojoStatus(String instamojoStatus) {
        this.instamojoStatus = instamojoStatus;
    }

}

