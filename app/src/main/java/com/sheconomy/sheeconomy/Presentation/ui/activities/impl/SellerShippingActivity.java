package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.sheconomy.sheeconomy.R;

public class SellerShippingActivity extends BaseActivity {
    private String sellerShipping;
    private WebView aboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shipping);



        sellerShipping=getIntent().getStringExtra("shipping_policy");


        initializeActionBar();
        setTitle(sellerShipping);
        initviews();

    }

    private void initviews() {
        aboutWebView = findViewById(R.id.product_description);
        aboutWebView.loadData(sellerShipping, "text/html", "UTF-8");

    }
}