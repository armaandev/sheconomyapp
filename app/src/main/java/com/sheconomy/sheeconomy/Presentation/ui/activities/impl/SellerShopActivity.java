package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.Shop;
import com.sheconomy.sheeconomy.Presentation.presenters.ShopPresenter;
import com.sheconomy.sheeconomy.Presentation.ui.activities.SellerShopView;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.BestSellingAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.FeaturedProductAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.ProductListingAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.listeners.ProductClickListener;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.AppConfig;
import com.sheconomy.sheeconomy.Utils.RecyclerViewMargin;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

public class SellerShopActivity extends BaseActivity implements SellerShopView, ProductClickListener {
    private String shop_name, shop_link;
    private SliderLayout sliderLayout;
    private ShopPresenter shopPresenter;
    private ProgressBar progress_bar;
    private TextView featured,top_selling, new_arrival;
    private NestedScrollView shop_details;
    private Button btn_seller_products;
    //new added properties
    private RelativeLayout about_us, feedback, contact_us, seller_policy, refund_policy,shipping_policy,payment_policy;
    private  TextView btn_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shop);

        shop_name = getIntent().getStringExtra("shop_name");
        shop_link = getIntent().getStringExtra("shop_link");

        initializeActionBar();
        setTitle(shop_name);
        initviews();

        shop_details.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);
        featured.setVisibility(View.GONE);
        top_selling.setVisibility(View.GONE);
        new_arrival.setVisibility(View.GONE);

        shopPresenter = new ShopPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        shopPresenter.getShopDetails(shop_link);


//        seller_policy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
//                intent.putExtra("title", "Seller Policy");
//                intent.putExtra("url", "policies/seller");
//                startActivity(intent);
//                Intent intent = new Intent(getApplicationContext(), SellerPoliciesOptActivity.class);
//                startActivity(intent);
//            }
//        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SellerShopActivity.this, "Blank ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initviews(){
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.stopAutoCycle();
        progress_bar = findViewById(R.id.item_progress_bar);
        featured = findViewById(R.id.featured_products_text);
        top_selling = findViewById(R.id.top_selling_products_text);
        new_arrival = findViewById(R.id.new_products_text);
        shop_details = findViewById(R.id.shop_details);
        btn_seller_products = findViewById(R.id.btnSellerProducts);
        //new added
//       store_home = findViewById(R.id.reviews);
//       seller_policy = findViewById(R.id.seller_policy);
       about_us = findViewById(R.id.about_us);
       feedback = findViewById(R.id.feedback);
       contact_us = findViewById(R.id.contact_us);
       btn_test=findViewById(R.id.btn_test);

       refund_policy=findViewById(R.id.seller_policy);
       shipping_policy=findViewById(R.id.seller_shipping_policy);
       payment_policy=findViewById(R.id.seller_payment_policy);
    }

    @Override
    public void onShopDetailsLoaded(Shop shop) {
        for (String photo : shop.getSliders()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(AppConfig.ASSET_URL + photo)
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
       //new line added
//      btn_test.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              Intent intent= new Intent(SellerShopActivity.this,SellerPaymentsDetailsActivity.class);
//              startActivity(intent);
//          }
//      });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerShopActivity.this, SellerPaymentsDetailsActivity.class);
                intent.putExtra("shop_name", shop.getUser().getEmail());
//                intent.putExtra("shop_link", productDetails.getUser().getShopLink());
                startActivity(intent);
            }
        });


        btn_seller_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerShopActivity.this, ProductListingActivity.class);
                intent.putExtra("title", shop.getName());
                intent.putExtra("url", shop.getLinks().getAll());
                startActivity(intent);
            }
        });

        refund_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerShopActivity.this, SellerRefundPolicyActivity.class);
                intent.putExtra("refund_policy", shop.getRefund_policy());
                startActivity(intent);

            }
        });

        shipping_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerShopActivity.this, SellerShippingPolicyActivity.class);
                intent.putExtra("shipping_policy", shop.getShipping_policy());
                startActivity(intent);
            }
        });
        payment_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerShopActivity.this, SellerPaymentPolicyActivity.class);
                intent.putExtra("payment_policy", shop.getPayment_policy());
                startActivity(intent);
            }
        });

     //new line for testing
//        about_us.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SellerShopActivity.this, shop.getAddress(), Toast.LENGTH_SHORT).show();
//            }
//        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SellerShopActivity.this,AboutUsActivity.class);
                intent.putExtra("about",shop.getAbout());
                startActivity(intent);
            }
        });
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SellerShopActivity.this, shop.getAddress(), Toast.LENGTH_LONG).show();
                Intent intent= new Intent(SellerShopActivity.this,ContectUsActivity.class);
                intent.putExtra("name",shop.getName());
                intent.putExtra("email",shop.getUser().getEmail());
                intent.putExtra("address",shop.getAddress());
                startActivity(intent);
            }
        });


        new ShopPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getFeaturedProducts(shop.getLinks().getFeatured());

        new ShopPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getTopSellingProducts(shop.getLinks().getTop());

        new ShopPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getNewProducts(shop.getLinks().getNew());
    }

    @Override
    public void setFeaturedProducts(List<Product> products) {
        featured.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.featured_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        FeaturedProductAdapter adapter = new FeaturedProductAdapter(this, products, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTopSellingProducts(List<Product> products) {
        top_selling.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.top_selling);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        BestSellingAdapter adapter = new BestSellingAdapter(this, products, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setNewProducts(List<Product> products) {
        new_arrival.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.new_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(this,10), 2);
        recyclerView.addItemDecoration(decoration);
        ProductListingAdapter adapter = new ProductListingAdapter(getApplicationContext(), products, this);
        recyclerView.setAdapter(adapter);

        progress_bar.setVisibility(View.GONE);
        shop_details.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProductItemClick(Product product) {
        //product click lister
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);

    }
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
