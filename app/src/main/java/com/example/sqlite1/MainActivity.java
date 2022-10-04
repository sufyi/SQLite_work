package com.example.sqlite1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kotlin.contracts.Returns;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,dob;
    Button insert, update,delete,view;
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edt1);
        contact = findViewById(R.id.edt2);
        dob = findViewById(R.id.edt3);
        insert = findViewById(R.id.btn1);
        update = findViewById(R.id.btn2);
        delete = findViewById(R.id.btn3);
        view = findViewById(R.id.bt4);
        dBhelper = new DBhelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = dBhelper.insertuserdata(nameTXT, contactTXT, dobTXT);

                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "new  Entry inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "new Entry not inserted", Toast.LENGTH_LONG).show();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT=dob.getText().toString();
                Boolean checkupdatedata = dBhelper.updateuserdata(nameTXT,contactTXT,dobTXT);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this ,"new  update inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"new update not inserted",Toast.LENGTH_LONG).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = dBhelper.deletedata(nameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "entry delete", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "NO entry delete", Toast.LENGTH_LONG).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=dBhelper.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this,"no entry exits",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("name :"+res.getString(0) +"\n" );
                    buffer.append("contact :"+res.getString(1) +"\n" );
                    buffer.append("dob :"+res.getString(2) +"\n" );
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("user entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}