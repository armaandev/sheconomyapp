package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.sheconomy.sheeconomy.Network.response.AppSettingsResponse;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.AccountFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.CartFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.CategoriesFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.HomeFragment;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.impl.ProductSearchFragment;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.CustomToast;
import com.sheconomy.sheeconomy.Utils.UserPrefs;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.sheconomy.sheeconomy.domain.interactors.AppSettingsInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.AppSettingsInteractorImpl;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

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
//new drawer
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private static NavigationView navigationView;


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

//      Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout=findViewById(R.id.drawerLayout);
       navigationView=findViewById(R.id.navigation_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
//        getSupportActionBar().setIcon(android.R.color.holo_blue_dark);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               return true;
           }
       });

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

        //new line for test
//        drawerLayout=findViewById(R.id.drawerLayout);
//        navigationView=findViewById(R.id.navigation_view);
//
//       toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
//       drawerLayout.addDrawerListener(toggle);
//       toggle.syncState();
//       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//           @Override
//           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               return true;
//           }
//       });
    //end line for test

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