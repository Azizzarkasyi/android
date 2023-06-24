package com.aziz.myapplication;

import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
FloatingActionButton tv;
database dbhelper;
Cursor cursor;

    String[] nam,nPm,tgl;
    int[]id;
ListView listView;
String[] tes;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma = this;
        dbhelper = new database(this);
        refes();

        tv = findViewById(R.id.add);



        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),tambahmusic.class);
                startActivity(intent);
            }
        });


    }
    public void refes(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa", null);
        tes = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            tes[cc] = cursor.getString(0).toString();
    }
        listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tes));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                final String selection = tes[i];
                Intent intent = new Intent(getApplicationContext(),tanmpil.class);
                intent.putExtra("nama",selection);
                startActivity(intent);
             }
        });
    }
}




