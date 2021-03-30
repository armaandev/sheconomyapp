package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Presentation.presenters.SellerPaymentsPresenter;
import com.sheconomy.sheeconomy.Presentation.presenters.ShopPresenter;
import com.sheconomy.sheeconomy.Presentation.ui.activities.SellerPaymentsView;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.BrandAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.SellerPaymentsTestAdapter;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.AppConfig;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

public class SellerPaymentsDetailsActivity extends BaseActivity implements SellerPaymentsView {
//     TextView payPallKey,payPallMid;
//     Button email;
    RecyclerView recyclerView;
    SellerPaymentsPresenter sellerPaymentsPresenter;
    String shopName;
//    SellerPayments sellerPayments=new SellerPayments();
    String link;

    SellerPaymentsTestAdapter sellerPaymentsTestAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_payments_details);

        shopName = getIntent().getStringExtra("shop_name");

        initializeActionBar();
        setTitle(shopName);

//        initviews();
//    payPallKey = findViewById(R.id.payPalKey1);
//  payPallKey.setText(sellerPayments.getSellerId());


//        sellerPaymentsPresenter = new SellerPaymentsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
//        sellerPaymentsPresenter.getSellerPayment();

//       new SellerPaymentsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getSellerPayment();
    }


    public void onSellerDetailsLoaded(List<SellerPayments> sellerPayments) {

//        Toast.makeText(this, sellerPayments.get(0).getPaypalEmail(), Toast.LENGTH_SHORT).show();
//        payPallKey.setText(sellerPayments.get(0).getPaypalEmail());

        Toast.makeText(this, sellerPayments.get(0).getSellerId(), Toast.LENGTH_SHORT).show();
//        if(sellerPayments.size()>0 ){
//            Toast.makeText(this, "List is not Empty", Toast.LENGTH_SHORT).show();
//        }
//          else{
//            Toast.makeText(this, "List is Empty", Toast.LENGTH_SHORT).show();
//        }


//       recyclerView = findViewById(R.id.recyclerView);
//        GridLayoutManager horizontalLayoutManager
//                = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        SellerPaymentsTestAdapter adapter = new SellerPaymentsTestAdapter( sellerPayments, this);
//        recyclerView.setAdapter(adapter);

    }


    }
