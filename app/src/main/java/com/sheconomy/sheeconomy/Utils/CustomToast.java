package com.sheconomy.sheeconomy.Utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.sheconomy.sheeconomy.R;

public class CustomToast {
    public static void showToast(Activity activity, String msg, int color){

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        LinearLayout linearLayout = layout.findViewById(R.id.toast_layout_root);

        TextView toastMessage = (TextView) layout.findViewById(R.id.toastMessage);

        Drawable background = linearLayout.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(activity, color));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(ContextCompat.getColor(activity,color));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(ContextCompat.getColor(activity,color));
        }

        layout.setBackground(background);
        toastMessage.setText(msg);

        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(layout);
        toast.show();

//        CookieBar.dismiss(activity);
//        CookieBar.build(activity)
//                .setTitle(R.string.app_name)
//                .setMessage(msg)
//                .setBackgroundColor(color)
//                .setAnimationIn(R.anim.slide_in_top, R.anim.slide_in_top)
//                .setAnimationOut(R.anim.slide_out_top, R.anim.slide_out_top)
//                .setDuration(1000)
//                .show();
    }
}
