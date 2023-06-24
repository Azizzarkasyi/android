package com.aziz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class tanmpil extends AppCompatActivity {
    TextView tvm1, tvm2, tvm3;
    Cursor cursor;
    ImageButton dlt,edt;
    Button kml;
    String[]tes;
    database dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanmpil);
        dbhelper = new database(this);
        tvm1 = findViewById(R.id.tampilnama);
        tvm2 = findViewById(R.id.tampilnpm);
        tvm3 = findViewById(R.id.tampiltanggal);
        kml = findViewById(R.id.kembali);
        dlt = findViewById(R.id.hapusmain);
        edt = findViewById(R.id.editmain);


        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa where nama ='"+
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tvm1.setText(cursor.getString(0).toString());
            tvm2.setText(cursor.getString(1).toString());
            tvm3.setText(cursor.getString(2).toString());

        }
        kml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase= dbhelper.getWritableDatabase();
                long result =sqLiteDatabase.delete("mahasiswa","nama='"+tvm1.getText().toString()+"'",null);
                if (result==-1){
                    Toast.makeText(tanmpil.this, "fail", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tanmpil.this, "susces", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),edit.class);
                intent.putExtra("nama",tvm1.getText().toString());
                startActivity(intent);
            }
        });

    }

}