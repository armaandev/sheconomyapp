package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheconomy.sheeconomy.Models.Product;
import com.sheconomy.sheeconomy.Models.ProductDetails;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Models.ShippingDetails;
import com.sheconomy.sheeconomy.Network.response.AddToCartResponse;
import com.sheconomy.sheeconomy.Network.response.AddToWishlistResponse;
import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;
import com.sheconomy.sheeconomy.Network.response.AuthResponse;
import com.sheconomy.sheeconomy.Network.response.CheckWishlistResponse;
import com.sheconomy.sheeconomy.Network.response.RemoveWishlistResponse;
import com.sheconomy.sheeconomy.Network.response.VariantResponse;
import com.sheconomy.sheeconomy.Presentation.presenters.ProductDetailsPresenter;
import com.sheconomy.sheeconomy.Presentation.ui.activities.ProductDetailsView;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.BestSellingofSellerAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.FeaturedProductAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.listeners.ProductClickListener;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.AppConfig;
import com.sheconomy.sheeconomy.Utils.CustomToast;
import com.sheconomy.sheeconomy.Utils.UserPrefs;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsView, ProductClickListener {
    private String product_name, link, top_selling_link;
    private String share_product;
    private VariantResponse variantResponse;
    private SliderLayout sliderLayout;
    private TextView name;
    private RatingBar ratingBar;
    private TextView rating_count, price_range;
    private ImageView shop_logo, heart_icon;
    private TextView shop_name;
    private RelativeLayout buying_option, specification, reviews, weightMsg,seller_policy, return_policy, support_policy;
    private RecyclerView related_products, top_selling;
    private NestedScrollView product_details;
    private LinearLayout product_buttons;
    private ProgressBar progress_bar;
    private Button addTocart, buyNow;
    private ProductDetails productDetails = null;
    private ProgressDialog progressDialog;
    private AuthResponse authResponse;
    private ProductDetailsPresenter productDetailsPresenter;
    private CardView shop_info, image_card;
    private boolean isBuyNow = false;
    private Button btn_shop;
    private RelativeLayout share;
    private TextView prodct_stock;
    //private TextView tvBuying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        product_name = getIntent().getStringExtra("product_name");
        share_product = getIntent().getStringExtra("share_product");
        link = getIntent().getStringExtra("link");
        top_selling_link = getIntent().getStringExtra("top_selling");

        initializeActionBar();
        setTitle(product_name);
        initviews();

        progress_bar.setVisibility(View.VISIBLE);
        product_details.setVisibility(View.INVISIBLE);
        product_buttons.setVisibility(View.INVISIBLE);
        image_card.setVisibility(View.GONE);


        productDetailsPresenter = new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productDetailsPresenter.getProductDetails(link);
        //prodct_stock.setText("instock");

       specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ProductDescriptionActivity.class);
                intent.putExtra("product_name", productDetails.getName());
                intent.putExtra("description", productDetails.getDescription());
                startActivity(intent);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, ProductReviewActivity.class);
                intent.putExtra("url", productDetails.getLinks().getReviews());
                startActivity(intent);
            }
        });
        buying_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBuyingOptionActivity();
            }
        });
        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processAddToCart();
            }
        });
      share.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              processToshare();
          }
      });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBuyNow = true;
                processAddToCart();
            }
        });
    }

    private void processToshare() {
        share_product = getIntent().getStringExtra("share_product");
        Intent sendIntent=new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT,share_product);
        startActivity(sendIntent);
//        Log.d("Adnan", share_product);
    }
    private void processAddToCart(){
        if(productDetails != null && (productDetails.getChoiceOptions().size() > 0 || productDetails.getColors().size() > 0)){
            startBuyingOptionActivity();
        }
        else {
            AuthResponse authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
            if(authResponse != null && authResponse.getUser() != null){
                progressDialog.setMessage("Adding item to your shopping cart. Please wait.");
                progressDialog.show();
                productDetailsPresenter.addToCart(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId(), null);
            }
            else {
                startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 100);
                finish();
            }
        }
    }
          private void startBuyingOptionActivity(){
        if(productDetails != null && (productDetails.getChoiceOptions().size() > 0 || productDetails.getColors().size() > 0)){
            Intent intent = new Intent(getApplicationContext(), BuyingOptionsActivity.class);
            intent.putExtra("product_details", productDetails);
            startActivity(intent);
        }
        else{
            CustomToast.showToast(this, "This product doesn't have any buying options.", R.color.colorWarning);
           buying_option.setVisibility(View.GONE);
        }
    }
    private void initviews(){
        product_details = findViewById(R.id.product_details);
        product_buttons = findViewById(R.id.product_buttons);
        progress_bar = findViewById(R.id.item_progress_bar);
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.stopAutoCycle();
        name = findViewById(R.id.product_name);
        ratingBar = findViewById(R.id.product_rating);
        rating_count = findViewById(R.id.product_rating_count);
        price_range = findViewById(R.id.product_price_range);
        shop_logo = findViewById(R.id.shop_logo);
        heart_icon = findViewById(R.id.heart_icon);
        shop_name = findViewById(R.id.shop_name);
        buying_option = findViewById(R.id.buying_option);
        specification = findViewById(R.id.specification);
        //description = findViewById(R.id.description);
        reviews = findViewById(R.id.reviews);
//      seller_policy = findViewById(R.id.seller_policy);
//      return_policy = findViewById(R.id.return_policy);
//      support_policy = findViewById(R.id.support_policy);
        related_products = findViewById(R.id.related_products);
        top_selling = findViewById(R.id.top_selling);
        addTocart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buyNow);
        progressDialog = new ProgressDialog(this);
        shop_info = findViewById(R.id.shop_info);
        image_card = findViewById(R.id.image_card);
        //new button
        btn_shop=findViewById(R.id.btn_shop);
        //tvBuying=findViewById(R.id.tvBuying);
        share=findViewById(R.id.share);
        prodct_stock=findViewById(R.id.currentStock);
        weightMsg= findViewById(R.id.weightMsg);
    }

    @Override
    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
        for (String photo : productDetails.getPhotos()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(AppConfig.ASSET_URL + photo)
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        image_card.setVisibility(View.VISIBLE);

        name.setText(productDetails.getName());

        ratingBar.setRating(productDetails.getRating());
        rating_count.setText("("+productDetails.getRatingCount()+")");
        prodct_stock.setText("("+productDetails.getCurrentStock()+")");
        
        if(productDetails.getShippingsDetails().size()== 0 && productDetails.getPaymentsDetails().size() == 0){
            Toast.makeText(this, "Payment option is not available for this seller", Toast.LENGTH_SHORT).show();
            buyNow.setVisibility(View.GONE);
            addTocart.setVisibility(View.GONE);
            buying_option.setVisibility(View.GONE);
            weightMsg.setVisibility(View.VISIBLE);
        }
        for(SellerPayments sellerPayments : productDetails.getPaymentsDetails()){
//            if(sellerPayments.getPaymentStatus().equals("1")|| sellerPayments.getRazorpayStatus().equals("1")
//                    || sellerPayments.getStripeStatus().equals("1") || sellerPayments.getInstamojoStatus().equals("1"))
            if(sellerPayments.getPaymentStatus().equals("1") && sellerPayments.getPaypalMid() !="" && sellerPayments.getPaymentStatus() !="")
            {
                buyNow.setVisibility(View.VISIBLE);
                addTocart.setVisibility(View.VISIBLE);
                buying_option.setVisibility(View.VISIBLE);
            }

            else if (sellerPayments.getRazorpayStatus().equals("1") && sellerPayments.getRazorpayKey()!="" && sellerPayments.getRazorpaySecret()!=""){
                buyNow.setVisibility(View.VISIBLE);
                addTocart.setVisibility(View.VISIBLE);
                buying_option.setVisibility(View.VISIBLE);
            }

            else if (sellerPayments.getStripeStatus().equals("1") && sellerPayments.getStripeKey()!="" && sellerPayments.getStripeSecret()!=""){
                buyNow.setVisibility(View.VISIBLE);
                addTocart.setVisibility(View.VISIBLE);
                buying_option.setVisibility(View.VISIBLE);
            }
            else if (sellerPayments.getInstamojoStatus().equals("1") && sellerPayments.getInstamojoKey()!="" && sellerPayments.getInstamojoToken()!=""){
                buyNow.setVisibility(View.VISIBLE);
                addTocart.setVisibility(View.VISIBLE);
                buying_option.setVisibility(View.VISIBLE);
            }
            else{
                buyNow.setVisibility(View.GONE);
                addTocart.setVisibility(View.GONE);
                buying_option.setVisibility(View.GONE);
                weightMsg.setVisibility(View.VISIBLE);
            }
        }
          int count=0;
         for (ShippingDetails shippingDetails : productDetails.getShippingsDetails()) {
            if(shippingDetails.getShippingType().equals("local") || shippingDetails.getShippingType().equals("regional")
                    || shippingDetails.getShippingType().equals("national") || shippingDetails.getShippingType().equals("international")){
                    count ++;
                    if(count == 3){
                    buyNow.setVisibility(View.VISIBLE);
                    addTocart.setVisibility(View.VISIBLE);
                    buying_option.setVisibility(View.VISIBLE);
//                  weightMsg.setVisibility(View.VISIBLE);
                    weightMsg.setVisibility(View.GONE);
                }
            }
          if(count < 3){
              buyNow.setVisibility(View.GONE);
              addTocart.setVisibility(View.GONE);
              buying_option.setVisibility(View.GONE);
              weightMsg.setVisibility(View.VISIBLE);
          }
        }
        if(productDetails.getWeight().equals("")){
            buyNow.setVisibility(View.GONE);
            addTocart.setVisibility(View.GONE);
            buying_option.setVisibility(View.GONE);
            weightMsg.setVisibility(View.VISIBLE);
            weightMsg.setBackgroundDrawable(getResources().getDrawable(R.drawable.out_of_stock_shape));
        }
     //For out of  Stock and in stock
        if(productDetails.getCurrentStock() <= 0 ){
//       Toast.makeText(this, "Out Of Stock", Toast.LENGTH_LONG).show();
          TextView  product_stock_msg=findViewById(R.id.product_stock_msg);
           product_stock_msg.setText("Out Of stock");
//          product_stock_msg.getResources().getDrawable(R.drawable.in_stock_shape);
            product_stock_msg.setBackgroundDrawable( getResources().getDrawable(R.drawable.out_of_stock_shape) );
            addTocart.setVisibility(View.GONE);
            buyNow.setVisibility(View.GONE);
            buying_option.setVisibility(View.GONE);
//          product_stock_msg.setBackgroundResource(R.color.blue);
        }
        else{
//          Toast.makeText(this, "In-Stock", Toast.LENGTH_LONG).show();
            TextView  product_stock_msg=findViewById(R.id.product_stock_msg);
            product_stock_msg.setText(" In-stock");
            product_stock_msg.setBackgroundDrawable( getResources().getDrawable(R.drawable.in_stock_shape) );
        }
        authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
        if(authResponse != null && authResponse.getUser() != null){
            productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
        }
        else {
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 100);
                    finish();
                }
            });
        }

        if (!productDetails.getAddedBy().equals("admin")){
            Glide.with(this).load(AppConfig.ASSET_URL + productDetails.getUser().getShopLogo()).into(shop_logo);
            shop_name.setText(productDetails.getUser().getShopName());
            shop_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductDetailsActivity.this, SellerShopActivity.class);
                    intent.putExtra("shop_name", productDetails.getUser().getShopName());
                    intent.putExtra("shop_link", productDetails.getUser().getShopLink());
                    startActivity(intent);
                }
            });
            //new on click
            btn_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductDetailsActivity.this, SellerShopActivity.class);
                    intent.putExtra("shop_name", productDetails.getUser().getShopName());
                    intent.putExtra("shop_link", productDetails.getUser().getShopLink());
                    startActivity(intent);
                }
            });
        }
        else {
            AppSettingsResponse appSettingsResponse = new UserPrefs(this).getAppSettingsPreferenceObjectJson("app_settings_response");
            if (appSettingsResponse != null){
                Glide.with(this).load(AppConfig.ASSET_URL + appSettingsResponse.getData().get(0).getLogo()).into(shop_logo);
            }
        }

        if(productDetails.getPriceLower().equals(productDetails.getPriceHigher())){
            price_range.setText(AppConfig.convertPrice(this, productDetails.getPriceLower()));
        }
        else {
            price_range.setText(AppConfig.convertPrice(this, productDetails.getPriceLower())+"-"+AppConfig.convertPrice(this, productDetails.getPriceHigher()));
        }
        progress_bar.setVisibility(View.GONE);
        product_details.setVisibility(View.VISIBLE);
        product_buttons.setVisibility(View.VISIBLE);

        new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getRelatedProducts(productDetails.getLinks().getRelated());

        new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getTopSellingProducts(top_selling_link);
    }

    @Override
    public void setRelatedProducts(List<Product> relatedProducts) {
        RecyclerView recyclerView = findViewById(R.id.related_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        FeaturedProductAdapter adapter = new FeaturedProductAdapter(this, relatedProducts, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getApplicationContext(), 10)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTopSellingProducts(List<Product> topSellingProducts) {
        RecyclerView recyclerView = findViewById(R.id.top_selling);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        BestSellingofSellerAdapter adapter = new BestSellingofSellerAdapter(this, topSellingProducts, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getApplicationContext(), 10)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setAddToCartMessage(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (isBuyNow){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("message", addToCartResponse.getMessage());
            intent.putExtra("position", "cart");
            startActivity(intent);
            finish();
        }
        else {
            CustomToast.showToast(this, addToCartResponse.getMessage(), R.color.colorSuccess);
        }
    }
    @Override
    public void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage) {
        CustomToast.showToast(this, addToWishlistMessage.getMessage(), R.color.colorSuccess);
        heart_icon.setImageResource(R.drawable.ic_heart_filled);
        productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
    }

    @Override
    public void onCheckWishlist(CheckWishlistResponse checkWishlistResponse) {
        if(checkWishlistResponse.getIsInWishlist()){
            heart_icon.setImageResource(R.drawable.ic_heart_filled);
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsPresenter.removeFromWishlist(authResponse.getAccessToken(), checkWishlistResponse.getWishlistId());
                }
            });
        }
        else{
            heart_icon.setImageResource(R.drawable.ic_heart);
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsPresenter.addToWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
                }
            });
        }
    }
    @Override
    public void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse) {
        heart_icon.setImageResource(R.drawable.ic_heart);
        CustomToast.showToast(this, removeWishlistResponse.getMessage(), R.color.colorSuccess);
        productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }
}
