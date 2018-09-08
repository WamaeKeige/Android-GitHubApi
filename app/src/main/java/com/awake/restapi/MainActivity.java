package com.awake.restapi;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
TextInputEditText txtEdit;
Button inputBtn;
private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEdit = (TextInputEditText) findViewById(R.id.txtedit);
        inputBtn = (Button)findViewById(R.id.button);
    }

    public void getUserData(View view) {
        i = new Intent(MainActivity.this, UserActivity.class);
        i.putExtra("STRING_I_NEED", txtEdit.getText().toString());
        startActivity(i);
    }
}
