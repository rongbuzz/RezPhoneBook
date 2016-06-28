package com.example.user.rezphonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addPhoneNum extends AppCompatActivity {

    EditText edname, ephone;
    dbHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_num);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edname = (EditText) findViewById(R.id.editText);
        ephone =(EditText) findViewById(R.id.editText2);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addContact(View btn){
        //get test from input

        String name = edname.getText().toString().trim();
        String phone= ephone.getText().toString().trim();
        ContactFolder values =new ContactFolder(name,phone);

        if(dbhelper.insertContact(values) != -1){
            setResult(RESULT_OK);
            finish();
        }
    }

}
