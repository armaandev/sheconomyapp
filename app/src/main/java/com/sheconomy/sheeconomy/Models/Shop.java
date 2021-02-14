package com.sheconomy.sheeconomy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shop {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("sliders")
    @Expose
    private List<String> sliders = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("google")
    @Expose
    private String google;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("links")
    @Expose
    private ShopLink links;

    @SerializedName("about")
    @Expose
    private String about;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("refund_policy")
    @Expose
    private String refund_policy;

    @SerializedName("shipping_policy")
    @Expose
    private String shipping_policy;

    @SerializedName("payment_policy")
    @Expose
    private String payment_policy;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getSliders() {
        return sliders;
    }

    public void setSliders(List<String> sliders) {
        this.sliders = sliders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public ShopLink getLinks() {
        return links;
    }

    public void setLinks(ShopLink links) {
        this.links = links;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getRefund_policy() { return refund_policy; }
    public void setRefund_policy(String refund_policy) {
        this.refund_policy = refund_policy;
    }

    public String getShipping_policy() { return shipping_policy; }
    public void setShipping_policy(String shipping_policy) {
        this.shipping_policy = shipping_policy;
    }

    public String getPayment_policy() { return payment_policy; }
    public void setPayment_policy(String payment_policy) {
        this.payment_policy = payment_policy;
    }
}
