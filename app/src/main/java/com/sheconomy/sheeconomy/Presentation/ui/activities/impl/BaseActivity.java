package com.sheconomy.sheeconomy.Presentation.ui.activities.impl;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sheconomy.sheeconomy.R;

import org.w3c.dom.Text;

public class BaseActivity extends AppCompatActivity {
    protected ImageButton cart,search;
    protected TextView title;
  //  private Button search;
    public void initializeActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setElevation(2);

        View view =getSupportActionBar().getCustomView();

        cart = view.findViewById(R.id.action_bar_cart);
        search = view.findViewById(R.id.action_bar_search);
        title = view.findViewById(R.id.nav_title);

        cart.setVisibility(View.GONE);
        search.setVisibility(View.GONE);
    }

    public void setTitle(String s){
        title.setText(s);
    }


}
