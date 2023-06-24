package com.aziz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class signup extends AppCompatActivity {
EditText emailsign,usersign,passsign,compasssign;
Button sign1;
TextView suges,log;

database dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        dbhelper = new database(this);
        emailsign =findViewById(R.id.email);
        usersign =findViewById(R.id.usernamesign);
        passsign =findViewById(R.id.passwordsign);
        compasssign =findViewById(R.id.confirmpassword);
        sign1 =findViewById(R.id.bt2);
        suges =findViewById(R.id.sugest2);
        log =findViewById(R.id.tologin);

        sign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailsign.getText().toString();
                String user = usersign.getText().toString();
                String pass = passsign.getText().toString();
                String compass = compasssign.getText().toString();

                if (email.equals("") && user.equals("") && pass.equals("") && compass.equals(""))
                    Toast.makeText(signup.this, "wajib diisi semua", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(compass)) {
                        Boolean Check = dbhelper.checkEmail(email);
                        if (Check == false) {
                            Boolean req = dbhelper.insertdata(email,user,pass);
                            if (req == true) {
                                Toast.makeText(signup.this, "sign berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signup.this, "sign gagal", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signup.this, "email sudah ada", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signup.this, "sandi berbeda", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        log.setPaintFlags(log.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }
}