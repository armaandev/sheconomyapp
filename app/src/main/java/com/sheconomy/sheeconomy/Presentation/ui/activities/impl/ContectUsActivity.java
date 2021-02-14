package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sheconomy.sheeconomy.R;

public class ContectUsActivity extends BaseActivity {
    private TextView namet1,t2,t3,t4,t5;
    String name,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect_us);



        initializeActionBar();
        setTitle("Contact Us");
        initviews();
        name=getIntent().getStringExtra("name");
        address=getIntent().getStringExtra("address");
        email=getIntent().getStringExtra("email");

        namet1.setText("name"+name);
        t2.setText("address"+address);
        t3.setText("email"+email);

    }

    private void initviews() {
        namet1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);

    }

}