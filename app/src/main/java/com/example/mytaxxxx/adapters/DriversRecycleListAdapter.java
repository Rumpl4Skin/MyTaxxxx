package com.example.mytaxxxx.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaxxxx.R;
import com.example.mytaxxxx.data.Driver;
import com.example.mytaxxxx.data.User;
import com.example.mytaxxxx.holders.RecyclerViewDriverHolder;
import com.example.mytaxxxx.holders.RecyclerViewUserHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class DriversRecycleListAdapter extends RecyclerView.Adapter<RecyclerViewDriverHolder> {
    private List<Driver> drivers;
    private Context context;
    private DriversRecycleListAdapter adapter;

    public void setAdapter(DriversRecycleListAdapter adapter) {
        this.adapter = adapter;
    }

    public DriversRecycleListAdapter(List<Driver> drivers, Context context, DriversRecycleListAdapter adapter) {
        this.drivers = drivers;
        this.context = context;
        this.adapter = adapter;
    }

    public DriversRecycleListAdapter(List<Driver> users, Context context) {
        this.drivers = users;
        this.context = context;
    }

    public DriversRecycleListAdapter(List<Driver> users) {
        this.drivers = users;
    }


    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_driver;
    }

    @NonNull
    @Override
    public RecyclerViewDriverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewDriverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewDriverHolder holder, int position) {
        if(drivers.get(position).getName()==null) {
            holder.getRoot().setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
        holder.getFioView().setText(String.valueOf(drivers.get(position).getName()));
        holder.getPhoneView().setText(String.valueOf(drivers.get(position).getPhone()));
        holder.getCarView().setText(String.valueOf(drivers.get(position).getCarname()));

holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
//Получаем вид с файла prompt.xml, который применим для диалогового окна:
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.prompt_driver_del, null);

            //Создаем AlertDialog
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

            //Настраиваем prompt.xml для нашего AlertDialog:
            mDialogBuilder.setView(promptsView);

            //Настраиваем отображение поля для ввода текста в открытом диалоге:
            final TextView fioDr =promptsView.findViewById(R.id.txtFioDr);
            fioDr.setText(holder.getFioView().getText());
            //Настраиваем сообщение в диалоговом окне:
            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Удалить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    //Удаляем элемент из бд
                                    String DRIVER_KEY="Drivers";
                                    String USER_KEY="Users";
                                    DatabaseReference mDataBaseDrivers= FirebaseDatabase.getInstance().getReference(USER_KEY).child(DRIVER_KEY);
                                    mDataBaseDrivers.child(drivers.get(position).getUid()).removeValue();
                                    adapter.notifyDataSetChanged();
                                }
                            })
                    .setNegativeButton("Отмена",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            //Создаем AlertDialog:
            AlertDialog alertDialog = mDialogBuilder.create();

            //и отображаем его:
            alertDialog.show();
            return false;
        }
    });

    }
    @Override
    public int getItemCount() {
        if(drivers!=null)
        return drivers.size();
        else return 0;
    }
}
