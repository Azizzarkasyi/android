package com.aziz.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class tambahmusic extends AppCompatActivity {

    EditText user,npm,tgl;
    RadioGroup rd;
    RadioButton rb1 ,rb2;
    Button tam;

    database dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahmusic);

        dbhelper = new database(this);
        user = findViewById(R.id.nama);
        npm = findViewById(R.id.NPM);
        tgl = findViewById(R.id.tanggal);
        tam = findViewById(R.id.tambah);



        tam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String use = user.getText().toString();
                String nPm = npm.getText().toString();
                String tangg = tgl.getText().toString();
                        Boolean Check = dbhelper.insertdatamahasiswa(use,nPm,tangg);
                        if (Check == true) {
                                Toast.makeText(getApplicationContext(), "data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                MainActivity.ma.refes();
                                startActivity(intent);
                            } else {
                                Toast.makeText(tambahmusic.this, "data gagal di tambahkan", Toast.LENGTH_SHORT).show();
                            }

                    }

        });






    }
    public void checkButton(View v){
        int radiogrup = rd.getCheckedRadioButtonId();
        rd.findViewById(radiogrup);
        Toast.makeText(this, "selected", Toast.LENGTH_SHORT).show();

    }

}