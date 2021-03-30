package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.se.omapi.Session;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.sheconomy.sheeconomy.Models.ChangeCurrency;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.AccountFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.CartFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.CategoriesFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.HomeFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.ProductSearchFragment;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.CountryLocation;
import com.sheconomy.sheeconomy.Utils.CustomToast;
import com.sheconomy.sheeconomy.Utils.SessionForCountry;
import com.sheconomy.sheeconomy.Utils.UserPrefs;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.sheconomy.sheeconomy.domain.interactors.AppSettingsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.ChangeCurrencyInteractor;
import com.sheconomy.sheeconomy.domain.interactors.SellerPaymentsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.AppSettingsInteractorImpl;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sheconomy.sheeconomy.domain.interactors.impl.ChangeCurrencyInteractorImp;
import com.sheconomy.sheeconomy.domain.interactors.impl.SellerPaymentsInteratorImp;

import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity implements AppSettingsInteractor.CallBack {

    final Fragment homeFragment = new HomeFragment();
    final Fragment categoriesFragment = new CategoriesFragment();
    private Fragment cartFragment = new CartFragment();
    private Fragment accountFragment = new AccountFragment();
    private Fragment searchFragment = new ProductSearchFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = homeFragment;
    public static BottomNavigationView navView;
    private ImageButton cart,search;
    //private Button search;
    private TextView title;
    FusedLocationProviderClient fusedLocationProviderClient;

     String Country_name = "";
    
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    title.setText("SHEconomy");
                    loadFragment(homeFragment);
                    break;
                case R.id.navigation_categories:
                    title.setText("Categories");
                    loadFragment(categoriesFragment);
                    break;
                case R.id.navigation_search:
                    title.setText("Search");
                    loadFragment(searchFragment);
                    break;
                case R.id.navigation_cart:
                    title.setText("Shopping Cart");
                    loadFragment(cartFragment);
                    break;
                case R.id.navigation_account:
                    title.setText("My Account");
                    loadFragment(accountFragment);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
              if(ActivityCompat.checkSelfPermission(MainActivity.this,
                      Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED )
              {
//                  getLocation();
                  fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                      @Override
                      public void onComplete(@NonNull Task<Location> task) {
                          //Initialize Location
                          Location location = task.getResult();
                            if(location != null){
                              //Initialize address
                              try {
                                   //Initialize geocoder
                                  Geocoder geocoder = new Geocoder(MainActivity.this,Locale.getDefault());
                                  List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                  Currency currency = Currency.getInstance(addresses.get(0).getLocale());
                                   String country_name = addresses.get(0).getCountryName();


                                   if(country_name.equals("United States")) {

                                       new ChangeCurrencyInteractorImp(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                                               new ChangeCurrencyInteractor.CallBack() {
                                                   @Override
                                                   public void onChangeCurrenciesLoaded(List<ChangeCurrency> changeCurrencies) {
                                                       System.out.println("....success");
                                                       if (changeCurrencies.size() > 0) {
                                                           String n = changeCurrencies.get(0).getCode();
                                                       }
                                                   }

                                                   @Override
                                                   public void onChangeCurrenciesLoadedError() {

                                                       System.out.println("...failure");

                                                   }

                                               }, "Rupee").execute();
                                   }
                                    CustomToast.showToast(MainActivity.this, addresses.get(0).getLocality(), R.color.colorWarning);

                              } catch (IOException e) {

                                  e.printStackTrace();
                              }
                          }
                      }
                  });
              }
              else{
                  ActivityCompat.requestPermissions(MainActivity.this,
                          new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
              }

//        Dexter.withContext(this)
//                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new PermissionListener() {
//                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
//
//
//                    }
//                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
//                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
//                }).check();

       // Creating Session class
//
//        class Session {
//            private SharedPreferences prefs;
//
//            public Session(Context cntx) {
//                // TODO Auto-generated constructor stub
//                prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
//            }
//
//            public void setusename(String usename) {
//                prefs.edit().putString("usename", "Arman").commit();
//            }
//
//            public String getusename() {
//                String usename = prefs.getString("usename","");
//                return usename;
//            }
//        }
//              Session session = new Session(getApplicationContext());
//        Toast.makeText(this, session.getusename(), Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setElevation(0);

        View view =getSupportActionBar().getCustomView();

        cart = view.findViewById(R.id.action_bar_cart);
        search = view.findViewById(R.id.action_bar_search);
        title = view.findViewById(R.id.nav_title);

        title.setText(R.string.app_name);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navView.setSelectedItemId(R.id.navigation_cart);
                loadFragment(cartFragment);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navView.setSelectedItemId(R.id.navigation_search);
                loadFragment(searchFragment);
            }
        });

        navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3); // number of menu from left
        new QBadgeView(this).bindTarget(v).setBadgeText(String.valueOf(0)).setShowShadow(false);

        fm.beginTransaction().add(R.id.fragment_container, categoriesFragment, "categories").hide(categoriesFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, searchFragment, "search").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, cartFragment, "cart").hide(cartFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "home").commit();

        loadFragment(homeFragment);
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            if (fragment != homeFragment){
                cart.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
            }
            else {
                cart.setVisibility(View.VISIBLE);
                search.setVisibility(View.VISIBLE);
            }
            if(fragment == cartFragment){
                cartFragment = new CartFragment();
                fm.beginTransaction().remove(fragment).commitAllowingStateLoss();
                fm.beginTransaction().add(R.id.fragment_container, cartFragment, "cart").hide(cartFragment).commitAllowingStateLoss();
                fm.beginTransaction().hide(active).show(cartFragment).commitAllowingStateLoss();
                active = cartFragment;
            }
            else if (fragment == accountFragment){
                accountFragment = new AccountFragment();
                fm.beginTransaction().remove(fragment).commitAllowingStateLoss();
                fm.beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commitAllowingStateLoss();
                fm.beginTransaction().hide(active).show(accountFragment).commitAllowingStateLoss();
                active = accountFragment;
            }
            else{
                fm.beginTransaction().hide(active).show(fragment).commit();
                active = fragment;
            }
        }

        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getExtras() != null){
            String message = getIntent().getStringExtra("message");
            String position = getIntent().getStringExtra("position");

            CustomToast.showToast(this, message, R.color.colorSuccess);
            getIntent().removeExtra("message");
            getIntent().removeExtra("position");

            if(position.equals("cart")){
                loadFragment(cartFragment);
                navView.setSelectedItemId(R.id.navigation_cart);
            }
            else if (position.equals("account")){
                loadFragment(accountFragment);
                navView.setSelectedItemId(R.id.navigation_account);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (active == homeFragment){
            super.onBackPressed();
        }
        else {
            loadFragment(homeFragment);
            navView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            new AppSettingsInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).execute();
        }
        if (resultCode == Activity.RESULT_CANCELED) {

        }

    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        UserPrefs userPrefs = new UserPrefs(this);
        userPrefs.setAppSettingsPreferenceObject(appSettingsResponse, "app_settings_response");

        accountFragment = new AccountFragment();
        fm.beginTransaction().remove(accountFragment).commitAllowingStateLoss();
        fm.beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commitAllowingStateLoss();
        fm.beginTransaction().hide(active).show(accountFragment).commitAllowingStateLoss();
        active = accountFragment;
    }

    @Override
    public void onAppSettingsLoadedError() {

    }

}