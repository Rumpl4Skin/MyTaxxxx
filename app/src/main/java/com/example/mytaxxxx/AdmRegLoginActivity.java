package com.example.mytaxxxx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdmRegLoginActivity extends AppCompatActivity {

    TextView driverStatus;
    Button signInBtn;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;
    DatabaseReference DriverDatabaseRef;
    String OnlineDriverID;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_login);

        driverStatus = (TextView)findViewById(R.id.statusDriver);
        signInBtn = (Button) findViewById(R.id.signInAdm);
        emailET = (EditText) findViewById(R.id.admEmail);
        passwordET = (EditText) findViewById(R.id.admPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInDriver(email, password);
            }
        });

    }

    private void SignInDriver(String email, String password)
    {
        loadingBar.setTitle("Вход диспетчера");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        if(email.equals("admin@gmail.com")&&password.equals("11111111")){
            Toast.makeText(AdmRegLoginActivity.this, "Вход успешен!", Toast.LENGTH_SHORT).show();
            Intent driverIntent = new Intent(AdmRegLoginActivity.this, DispatcherActivity.class);
            startActivity(driverIntent);
        }
        else{
            Toast.makeText(AdmRegLoginActivity.this, "Неверный логин или пароль!", Toast.LENGTH_LONG).show();
            loadingBar.dismiss();
        }

    }
}
