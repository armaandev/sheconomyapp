package com.sheconomy.sheeconomy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheconomy.sheeconomy.Models.CartModel;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.Network.response.AuthResponse;
import com.sheconomy.sheeconomy.Network.response.MessageResponse;
import com.sheconomy.sheeconomy.Network.response.RemoveCartResponse;
import com.sheconomy.sheeconomy.Presentation.presenters.CartPresenter;
import com.sheconomy.sheeconomy.Presentation.ui.activities.impl.ShippingActivity;
import com.sheconomy.sheeconomy.Presentation.ui.adapters.CartListAdapter;
import com.sheconomy.sheeconomy.Presentation.ui.fragments.CartView;
import com.sheconomy.sheeconomy.Presentation.ui.listeners.CartItemListener;
import com.sheconomy.sheeconomy.R;
import com.sheconomy.sheeconomy.Threading.MainThreadImpl;
import com.sheconomy.sheeconomy.Utils.AppConfig;
import com.sheconomy.sheeconomy.Utils.CustomToast;
import com.sheconomy.sheeconomy.Utils.SwipeToDeleteCallback;
import com.sheconomy.sheeconomy.Utils.UserPrefs;
import com.sheconomy.sheeconomy.domain.executor.impl.ThreadExecutor;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;

import static com.sheconomy.sheeconomy.Presentation.ui.activities.impl.MainActivity.navView;

public class CartFragment extends Fragment implements CartView, CartItemListener {
    private View v;
    private CartPresenter cartPresenter;
    private AuthResponse authResponse;
    private Button btnCheckout;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private TextView total_amount;
    private double total = 0;
    private double shipping = 0;
    private double tax = 0;
    private int qty = 0;
    private TextView cart_empty_text;
    private int seller_id,id;
    private String razorpay_status="",razorpay_key="",razorpay_secret="";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cart, null);

        btnCheckout = v.findViewById(R.id.checkout);
        progressBar = v.findViewById(R.id.item_progress_bar);
        linearLayout = v.findViewById(R.id.checkout_button);
        total_amount = v.findViewById(R.id.total_amount);
        cart_empty_text = v.findViewById(R.id.cart_empty_text);

        linearLayout.setVisibility(View.GONE);

        cartPresenter = new CartPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");
        if(authResponse != null && authResponse.getUser() != null){
            cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            cart_empty_text.setVisibility(View.VISIBLE);
        }

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShippingActivity.class);
                intent.putExtra("total", total);
                intent.putExtra("shipping", shipping);
                intent.putExtra("tax", tax);
                intent.putExtra("seller_id",seller_id);
//
//                intent.putExtra("razorpay_status",razorpay_status);
//                intent.putExtra("razorpay_key",razorpay_key);
//                intent.putExtra("razorpay_secret",razorpay_secret);
//                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        return v;
    }
    private void updateCartBadge(List<CartModel> cartItems){
        linearLayout.setVisibility(View.VISIBLE);
        total = 0;
        qty = 0;

        for (CartModel cartModel: cartItems){
            total += (cartModel.getPrice()+cartModel.getTax()+cartModel.getShippingCost())*cartModel.getQuantity();
            shipping = cartModel.getShippingCost()*cartModel.getQuantity();
            tax = cartModel.getTax()+cartModel.getQuantity();
            qty += cartModel.getQuantity();
            //new line added for j
            seller_id =cartModel.getSeller_id();

//            for(SellerPayments sellerPayments : cartModel.getPaymentsDetails()){
//                razorpay_status = sellerPayments.getRazorpayStatus();
//                razorpay_key = sellerPayments.getRazorpayKey();
//                razorpay_secret = sellerPayments.getRazorpaySecret();


//            }

        }
        total_amount.setText(AppConfig.convertPrice(getContext(), total));

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3); // number of menu from left
        new QBadgeView(getActivity()).bindTarget(v).setBadgeText(String.valueOf(qty)).setShowShadow(false);
    }

    @Override
    public void setCartItems(List<CartModel> cartItems) {
        progressBar.setVisibility(View.GONE);
        updateCartBadge(cartItems);
        if (cartItems.size() > 0){
            RecyclerView recyclerView = v.findViewById(R.id.product_list);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(horizontalLayoutManager);
            CartListAdapter adapter = new CartListAdapter(getActivity(), cartItems, this);
            recyclerView.setAdapter(adapter);

            ItemTouchHelper itemTouchHelper = new
                    ItemTouchHelper(new SwipeToDeleteCallback(adapter));
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        else {
            cart_empty_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showRemoveCartMessage(RemoveCartResponse removeCartResponse) {
        CustomToast.showToast(getActivity(), removeCartResponse.getMessage(), R.color.colorSuccess);
        cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
    }

    @Override
    public void showCartQuantityUpdateMessage(MessageResponse messageResponse) {
        CustomToast.showToast(getActivity(), messageResponse.getMessage(), R.color.colorSuccess);
        cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
    }

    @Override
    public void onCartRemove(CartModel cartModel) {
        cartPresenter.removeCartItem(cartModel.getId(), authResponse.getAccessToken());
    }

    @Override
    public void onQuantityUpdate(int qty, CartModel cartModel) {
        cartPresenter.updateCartQuantity(cartModel.getId(), qty, authResponse.getAccessToken());
    }
}
