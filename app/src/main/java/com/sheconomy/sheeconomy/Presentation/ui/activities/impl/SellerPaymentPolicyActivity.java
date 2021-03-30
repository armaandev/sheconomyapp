package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.sheconomy.sheeconomy.R;

public class SellerPaymentPolicyActivity extends BaseActivity {
    private String sellerPayment;
    private WebView aboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_payment);

        sellerPayment=getIntent().getStringExtra("payment_policy");

        initializeActionBar();
        setTitle(sellerPayment);
        initviews();
    }

    private void initviews() {
        aboutWebView = findViewById(R.id.product_description);
        aboutWebView.loadData(sellerPayment, "text/html", "UTF-8");
    }
}