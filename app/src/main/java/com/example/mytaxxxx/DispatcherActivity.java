package com.example.mytaxxxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mytaxxxx.adapters.DriversRecycleListAdapter;
import com.example.mytaxxxx.adapters.UsersRecycleListAdapter;
import com.example.mytaxxxx.data.Driver;
import com.example.mytaxxxx.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DispatcherActivity extends AppCompatActivity {

    private RecyclerView recyclerUserView,recyclerDriverView;
    private DatabaseReference mDataBaseUsers;
    private DatabaseReference mDataBaseDrivers;
    private String USER_KEY="Users";
    private String CUSTOMER_KEY="Customers";
    private String DRIVER_KEY="Drivers";
    private ArrayList<User> users;
    private ArrayList<Driver> drivers;
    private UsersRecycleListAdapter userAdapter;
    private DriversRecycleListAdapter driverAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher);


        mDataBaseUsers= FirebaseDatabase.getInstance().getReference(USER_KEY).child(CUSTOMER_KEY);
        mDataBaseDrivers= FirebaseDatabase.getInstance().getReference(USER_KEY).child(DRIVER_KEY);
        users=new ArrayList<>();
        drivers=new ArrayList<>();
        recyclerUserView = findViewById(R.id.RecListUsers);
        recyclerDriverView = findViewById(R.id.RecListDrivers);
        userAdapter=new UsersRecycleListAdapter(users, this);
        userAdapter.setAdapter(userAdapter);

        driverAdapter=new DriversRecycleListAdapter(drivers, this,driverAdapter);
        driverAdapter.setAdapter(driverAdapter);
        recyclerUserView.setLayoutManager(new LinearLayoutManager(this));
        recyclerUserView.setAdapter(userAdapter);
        getAllUsers();

        recyclerDriverView.setLayoutManager(new LinearLayoutManager(this));
        recyclerDriverView.setAdapter(driverAdapter);
        getAllDrivers();
    }
    private void getAllUsers(){
        ValueEventListener vListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(users.size()>0) users.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {


                    User user=ds.getValue(User.class);
                    assert user!=null;
                    users.add(user);
                    }
                    catch (DatabaseException e){
                        //Toast.makeText(getBaseContext(),"Что то пошло не так: "+e.getMessage(),Toast.LENGTH_LONG).show();
                        continue;
                    }
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(),"Что то пошло не так: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
        mDataBaseUsers.addValueEventListener(vListener);
    }
    private void getAllDrivers(){
        ValueEventListener vListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(drivers.size()>0) drivers.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {

                        Driver driver=ds.getValue(Driver.class);
                        assert driver!=null;
                        drivers.add(driver);
                    }
                    catch (DatabaseException e){
                        //Toast.makeText(getBaseContext(),"Что то пошло не так: "+e.getMessage(),Toast.LENGTH_LONG).show();
                        continue;
                    }
                }
                driverAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(),"Что то пошло не так: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
        mDataBaseDrivers.addValueEventListener(vListener);
    }
}