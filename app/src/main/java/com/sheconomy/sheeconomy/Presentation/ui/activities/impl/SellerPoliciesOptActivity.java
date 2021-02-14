package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sheconomy.sheeconomy.Models.Shop;
import com.sheconomy.sheeconomy.R;

public class SellerPoliciesOptActivity extends BaseActivity {
    private TextView tvRefundPolicy,tvShippingPolicy,tvPaymentPolicy;
    private Shop shop=new Shop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_policies_opt);

        initializeActionBar();
        setTitle("Policies");
        initviews();

        tvRefundPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Return Policy");
                intent.putExtra("url", "policies/return");
                startActivity(intent);
            }
        });
        tvRefundPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Return Policy");
                intent.putExtra("url", "policies/return");
                startActivity(intent);
            }
        });

        tvShippingPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Shipping Policy");
                intent.putExtra("url", "policies/support");
                startActivity(intent);
            }
        });
        tvPaymentPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Payment Policy");
                intent.putExtra("url", "policies/support");
                startActivity(intent);
            }
        });
    }

    private void initviews() {
        tvRefundPolicy=findViewById(R.id.tvRefundPolicy);
        tvPaymentPolicy=findViewById(R.id.tvPaymentPolicy);
        tvShippingPolicy=findViewById(R.id.tvShippingPolicy);

    }
}