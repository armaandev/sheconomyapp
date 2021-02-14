package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.os.Bundle;
import android.webkit.WebView;

import com.sheconomy.sheeconomy.R;

public class AboutUsActivity extends BaseActivity {
//    String about_us, about_seller;
//  private String product_name,description;
    private String about;
    private WebView aboutWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about=getIntent().getStringExtra("about");


        initializeActionBar();
        setTitle(about);
        initviews();

    }

    private void initviews() {
        aboutWebView = findViewById(R.id.product_description);
        aboutWebView.loadData(about, "text/html", "UTF-8");

    }
}