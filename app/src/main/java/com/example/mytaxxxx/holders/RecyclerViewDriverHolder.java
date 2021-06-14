package com.example.mytaxxxx.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaxxxx.R;


public class RecyclerViewDriverHolder extends RecyclerView.ViewHolder {

    private TextView Fio;
    private TextView Phone;
    private TextView Car;
    private ConstraintLayout root;
    public RecyclerViewDriverHolder(@NonNull View itemView) {
        super(itemView);
        Fio = itemView.findViewById(R.id.txtDriverName);
        Phone = itemView.findViewById(R.id.txtNmbDriver);
        Car = itemView.findViewById(R.id.txtCar);
        root=itemView.findViewById(R.id.rootFrameDriver);

    }

    public TextView getFioView(){
        return Fio;
    }
    public TextView getPhoneView(){ return Phone; }
    public TextView getCarView() { return Car; }

    public ConstraintLayout getRoot() { return root; }
}