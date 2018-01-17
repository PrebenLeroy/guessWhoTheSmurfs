package com.example.guesswhothesmurfs.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.guesswhothesmurfs.R;

/**
 * Created by prebe on 17/01/2018.
 */

public class GameChooseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#316E92")));
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.full);
        layout.setBackgroundColor(Color.GRAY);
    }
}
