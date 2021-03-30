package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.sheconomy.sheeconomy.R;

public class SellerRefundPolicyActivity extends BaseActivity {
    private String sellerRefundPolicy;
    private WebView aboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_refund);


        sellerRefundPolicy=getIntent().getStringExtra("refund_policy");


        initializeActionBar();
        setTitle(sellerRefundPolicy);
        initviews();

    }

    private void initviews() {
        aboutWebView = findViewById(R.id.product_description);
        aboutWebView.loadData(sellerRefundPolicy, "text/html", "UTF-8");
    }
}