package com.aziz.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class login extends AppCompatActivity {

    EditText userlogin,paswlogin;
    TextView sign,suge;
    Button bt;
    database dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbhelper = new database(this);
        userlogin=findViewById(R.id.editTextTextPersonName);
        paswlogin=findViewById(R.id.password);
        sign=findViewById(R.id.tosign);
        suge=findViewById(R.id.sugest);
        bt=findViewById(R.id.bt1);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userlogin.getText().toString();
                String pass = paswlogin.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(login.this, "wajib diisi semua", Toast.LENGTH_SHORT).show();
                else {
                    Boolean res = dbhelper.checkEmailPassword(user,pass);
                    if (res == true){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else  {
                        Toast.makeText(login.this, "Username atau pasword salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        sign.setPaintFlags(sign.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });

    }
}