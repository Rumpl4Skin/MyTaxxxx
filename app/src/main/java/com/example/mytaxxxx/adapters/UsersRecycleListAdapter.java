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
import com.example.mytaxxxx.data.User;
import com.example.mytaxxxx.holders.RecyclerViewUserHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class UsersRecycleListAdapter extends RecyclerView.Adapter<RecyclerViewUserHolder> {
    private List<User> users;
    private Context context;
    private UsersRecycleListAdapter adapter;

    public UsersRecycleListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public UsersRecycleListAdapter(List<User> users, Context context, UsersRecycleListAdapter adapter) {
        this.users = users;
        this.context = context;
        this.adapter = adapter;
    }

    public void setAdapter(UsersRecycleListAdapter adapter) {
        this.adapter = adapter;
    }

    public UsersRecycleListAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_user;
    }

    @NonNull
    @Override
    public RecyclerViewUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUserHolder holder, int position) {
        holder.getFioView().setText(String.valueOf(users.get(position).getName()));
        holder.getPhoneView().setText(String.valueOf(users.get(position).getPhone()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_user_del, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final TextView fioDr =promptsView.findViewById(R.id.txtFioUs);
                fioDr.setText(holder.getFioView().getText());
                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Удалить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //Удаляем элемент из бд
                                        String CUSTOMER_KEY="Customers";
                                        String USER_KEY="Users";
                                        DatabaseReference mDataBaseDrivers= FirebaseDatabase.getInstance().getReference(USER_KEY).child(CUSTOMER_KEY);
                                        mDataBaseDrivers.child(users.get(position).getUID()).removeValue();
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
        if(users!=null)
        return users.size();
        else return 0;
    }
}
