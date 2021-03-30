package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Presentation.ui.activities.RazorPayView;
import com.sheconomy.sheeconomy.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RazorPayActivity extends BaseActivity implements RazorPayView, PaymentResultListener {
    Button payBtn;
    TextView tvRazor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay);
        Checkout.preload(getApplicationContext());

        initializeActionBar();
        setTitle("Razor Pay");
        initviews();

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(RazorPayActivity.this, "hello", Toast.LENGTH_SHORT).show();
                toMakePayment();
            }
        });
    }

    private void toMakePayment() {

//        List<SellerPayments> sellerPayments = new ArrayList<>();
//        for(int i=0 ; i<sellerPayments.size() ; i++){
//            SellerPayments sellerPayments1 = sellerPayments.get(i);  //getting single object from Arraylist
//          Toast.makeText(this, sellerPayments1.getSellerId(), Toast.LENGTH_SHORT).show();
//
//             tvRazor.setText(sellerPayments.get(0).getPaypalEmail());
//
//        }

//        SellerPayments model = sellerPayments.get(0);
//        tvRazor.setText(model.getPaypalEmail());

//        String word = model.getPaypalEmail();
//        String countWord= model.getRazorpayKey();

//        Toast.makeText(this, "word=  " +word+"  Occurance= "+ countWord, Toast.LENGTH_SHORT).show();


        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_geFvStRedSs5WV");

        checkout.setImage(R.drawable.ic_razorpayh);

        final Activity activity = this;
//
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Sheconomy");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000");//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    private void initviews() {
        payBtn=findViewById(R.id.btnRazorPay);
        tvRazor=findViewById(R.id.tvRazor);

    }

    @Override
    public void onPaymentSuccess(String s) {
     tvRazor.setText("Successful ID"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        tvRazor.setText("Failed and Cause is"+s);

    }
}