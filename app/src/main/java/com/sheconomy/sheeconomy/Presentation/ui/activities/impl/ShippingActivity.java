package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheconomy.sheeconomy.Models.ShippingAddress;
import com.sheconomy.sheeconomy.Models.User;
import com.sheconomy.sheeconomy.Network.response.AuthResponse;
import com.sheconomy.sheeconomy.Network.response.ProfileInfoUpdateResponse;
import com.sheconomy.sheeconomy.Network.response.ShippingInfoResponse;
import com.sheconomy.sheeconomy.Presentation.presenters.AccountInfoPresenter;
import com.sheconomy.sheeconomy.Presentation.ui.activities.AccountInfoView;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.ShippingAddressSelectAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.listeners.ShippingAddressListener;
import com.sheconomy.sheeconomy.Presentation.ui.listeners.ShippingAddressSelectListener;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.AppConfig;
import com.sheconomy.sheeconomy.Utils.CustomToast;
import com.sheconomy.sheeconomy.Utils.UserPrefs;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

public class ShippingActivity extends BaseActivity implements AccountInfoView, ShippingAddressListener, ShippingAddressSelectListener {
    private AuthResponse authResponse;
    private Button payment;
    private Double total = 0.0, shipping = 0.0, tax= 0.0;
    private RecyclerView recyclerView;
    private CardView card_new_address;
    private ShippingAddress shippingAddress = null;
    AlertDialog.Builder builder;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        total = getIntent().getDoubleExtra("total", 0.0);
        shipping = getIntent().getDoubleExtra("shipping", 0.0);
        tax = getIntent().getDoubleExtra("tax", 0.0);

        initializeActionBar();
        setTitle("Shipping Information");
        initviews();

        authResponse = new UserPrefs(this).getAuthPreferenceObjectJson("auth_response");
        if(authResponse != null && authResponse.getUser() != null){
            new AccountInfoPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getShippingAddresses(authResponse.getUser().getId(), authResponse.getAccessToken());
        }
    }

    private void initviews(){
        recyclerView = findViewById(R.id.rv_shipping_addresses);
        payment = findViewById(R.id.payment);
        card_new_address = findViewById(R.id.card_new_address);
        builder = new AlertDialog.Builder(this);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shippingAddress != null){
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("total", total);
                    intent.putExtra("shipping", shipping);
                    intent.putExtra("tax", tax);

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", authResponse.getUser().getName());
                    jsonObject.addProperty("email", authResponse.getUser().getEmail());
                    jsonObject.addProperty("address", shippingAddress.getAddress());
                    jsonObject.addProperty("country", shippingAddress.getCountry());
                    jsonObject.addProperty("city", shippingAddress.getCity());
                    jsonObject.addProperty("postal_code", shippingAddress.getPostalCode());
                    jsonObject.addProperty("phone", shippingAddress.getPhone());
                    jsonObject.addProperty("checkout_type", "logged");

                    intent.putExtra("shipping_address", jsonObject.toString());

                    startActivity(intent);
                }
                else {
                    CustomToast.showToast(ShippingActivity.this, "Go to Account Information and set shipping address.", R.color.colorWarning);
                }
            }
        });

        card_new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uncomment the below code to Set the message and title from the strings.xml file
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.shipping_address_add, null);
                builder.setView(dialogLayout);

                //Setting message manually and performing action on button click
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText input_address = dialogLayout.findViewById(R.id.input_address);
                        EditText input_city = dialogLayout.findViewById(R.id.input_city);
                        EditText input_country = dialogLayout.findViewById(R.id.input_country);
                        EditText input_postal_code = dialogLayout.findViewById(R.id.input_postal_code);
                        EditText input_phone = dialogLayout.findViewById(R.id.input_phone);

                        Boolean isValid = true;

                        if(input_address.getText().toString().length() <= 0){
                            TextInputLayout til = dialogLayout.findViewById(R.id.input_address_layout);
                            til.setError("Address is required");
                            isValid = false;
                        }
                        if(input_city.getText().toString().length() <= 0){
                            TextInputLayout til = dialogLayout.findViewById(R.id.input_city_layout);
                            til.setError("City is required");
                            isValid = false;
                        }

                        if(input_postal_code.getText().toString().length() <= 0){
                            TextInputLayout til = dialogLayout.findViewById(R.id.input_postal_code_layout);
                            til.setError("Postal code is required");
                            isValid = false;
                        }

                        if(input_country.getText().toString().length() <= 0){
                            TextInputLayout til = dialogLayout.findViewById(R.id.input_country_layout);
                            til.setError("Country is required");
                            isValid = false;
                        }

                        if(input_phone.getText().toString().length() <= 0){
                            TextInputLayout til = dialogLayout.findViewById(R.id.input_phone_layout);
                            til.setError("Phone number is required");
                            isValid = false;
                        }

                        if (isValid){
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("address", input_address.getText().toString());
                            jsonObject.addProperty("city", input_city.getText().toString());
                            jsonObject.addProperty("country", input_country.getText().toString());
                            jsonObject.addProperty("phone", input_phone.getText().toString());
                            jsonObject.addProperty("postal_code", input_postal_code.getText().toString());
                            jsonObject.addProperty("user_id", authResponse.getUser().getId());

                            new AccountInfoPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), ShippingActivity.this).addNewShippingRequest(jsonObject, authResponse.getAccessToken());

                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                alert = builder.create();
                alert.setTitle("Shipping Information");
                alert.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                });

                alert.show();
            }
        });
    }

    @Override
    public void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse) {

    }

    @Override
    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getApplicationContext(), 10)) );
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        ShippingAddressSelectAdapter adapter = new ShippingAddressSelectAdapter(getApplicationContext(), shippingAddresses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse) {
        CustomToast.showToast(this, shippingInfoResponse.getMessage(), R.color.colorSuccess);
        new AccountInfoPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getShippingAddresses(authResponse.getUser().getId(), authResponse.getAccessToken());
    }

    @Override
    public void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse) {

    }

    @Override
    public void setUpdatedUserInfo(User user) {

    }

    @Override
    public void onShippingAddressItemClick(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void onItemDeleteClick(ShippingAddress shippingAddress) {

    }
}
