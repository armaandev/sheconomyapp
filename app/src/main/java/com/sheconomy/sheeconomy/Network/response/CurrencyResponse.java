package com.sheconomy.sheeconomy.Network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sheconomy.sheeconomy.Models.Banner;
import com.sheconomy.sheeconomy.Models.ChangeCurrency;
import com.sheconomy.sheeconomy.Models.Currency;

import java.util.List;

public class CurrencyResponse {
    @SerializedName("data")
    @Expose
    private List<ChangeCurrency> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<ChangeCurrency> getData() {
        return data;
    }

    public void setData(List<ChangeCurrency> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
