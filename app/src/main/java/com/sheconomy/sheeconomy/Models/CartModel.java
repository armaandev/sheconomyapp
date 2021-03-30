package com.sheconomy.sheeconomy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product")
    @Expose
    private CartProduct cartProduct;
    @SerializedName("variation")
    @Expose
    private String variation;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("tax")
    @Expose
    private Double tax;
    @SerializedName("shipping_cost")
    @Expose
    private Double shippingCost;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("seller_id")
    @Expose
    private Integer seller_id;

    @SerializedName("seller_payment")
    @Expose
    private List<SellerPayments> paymentsDetails = null;
    public List<SellerPayments> getPaymentsDetails() {
        return paymentsDetails;
    }

    public void setPaymentsDetails(List<SellerPayments> paymentsDetails) {
        this.paymentsDetails = paymentsDetails;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CartProduct getProduct() {
        return cartProduct;
    }

    public void setProduct(CartProduct product) {
        this.cartProduct = product;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
