package com.example.mytaxxxx.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaxxxx.R;


public class RecyclerViewUserHolder extends RecyclerView.ViewHolder {

    private TextView Fio;
    private TextView Phone;

    public RecyclerViewUserHolder(@NonNull View itemView) {
        super(itemView);
        Fio = itemView.findViewById(R.id.txtUserName);
        Phone = itemView.findViewById(R.id.txtNmbUser);


    }

    public TextView getFioView(){
        return Fio;
    }
    public TextView getPhoneView(){
        return Phone;
    }
}