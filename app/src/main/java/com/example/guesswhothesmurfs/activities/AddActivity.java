package com.example.guesswhothesmurfs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guesswhothesmurfs.R;

/**
 * Created by prebe on 11/01/2018.
 */

public class AddActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnAdd;
    private EditText name;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.btnCancel = (Button) findViewById(R.id.button2);
        this.btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddActivity.super.finish();
            }
        });
    }
}
