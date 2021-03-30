package com.sheconomy.sheeconomy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheconomy.sheeconomy.Models.Brand;
import com.sheconomy.sheeconomy.Models.SellerPayments;
import com.sheconomy.sheeconomy.R;

import java.util.List;

public class SellerPaymentsTestAdapter extends RecyclerView.Adapter<SellerPaymentsTestAdapter.MyViewHolder>  {

    List<SellerPayments> mSellerPayments;
    Context context;

    public SellerPaymentsTestAdapter(List<SellerPayments> mSellerPayments, Context context) {
        this.mSellerPayments = mSellerPayments;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.seller_payments_key_test,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SellerPayments paymentsModel= mSellerPayments.get(position);
        holder.payPalKey.setText(paymentsModel.getPaypalKey());
        holder.payPalMid.setText(paymentsModel.getPaypalMid());
        holder.email.setText(paymentsModel.getPaypalEmail());

    }

    @Override
    public int getItemCount() {
        return mSellerPayments.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView payPalKey,payPalMid,email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            payPalKey=itemView.findViewById(R.id.payPalKey);
            payPalMid=itemView.findViewById(R.id.payPalMid);
            email=itemView.findViewById(R.id.email);
        }
    }


}
