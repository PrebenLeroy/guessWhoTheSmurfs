package com.example.guesswhothesmurfs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guesswhothesmurfs.R;

/**
 * Created by prebe on 11/01/2018.
 */

public class StartUpActivity extends AppCompatActivity {

    private Button btnAdd, btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.btnAdd = (Button) findViewById(R.id.btnAdd);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartUpActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        this.btnViewAll = (Button) findViewById(R.id.btnCharacters);
        this.btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
