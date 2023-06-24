package com.aziz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class edit extends AppCompatActivity {
    EditText nam,nPm,tgl;
    Button edi;
    database dbhelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbhelper = new database(this);

        nam =findViewById(R.id.namaedit);
        nPm =findViewById(R.id.NPMedit);
        tgl =findViewById(R.id.tanggaledit);
        edi =findViewById(R.id.edit);


        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nam.setText(cursor.getString(0).toString());
            nPm.setText(cursor.getString(1).toString());
            tgl.setText(cursor.getString(2).toString());



        edi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String use = nam.getText().toString();
                String npm = nPm.getText().toString();
                String tangg = tgl.getText().toString();
                Boolean Check = dbhelper.editdatamahasiswa(use,npm,tangg);
                if (Check == true) {
                    Toast.makeText(getApplicationContext(), "data berhasil di edit", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),tanmpil.class);
                    intent.putExtra("nama",nam.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(edit.this, "data gagal di edit", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}}