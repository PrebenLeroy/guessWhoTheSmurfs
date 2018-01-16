package com.example.guesswhothesmurfs.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.persistency.CharacterContract;

import java.util.ArrayList;

/**
 * Created by prebe on 11/01/2018.
 */

public class AddActivity extends AppCompatActivity {

    private Button btnCancel, btnAdd;
    private EditText name, description;

    private String charactername;
    private String charDescription;

    private ArrayList<String> list;
    private static final String TAG = AddActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.name);
        this.charactername = name.getText().toString();

        description = (EditText) findViewById(R.id.description);
        this.charDescription = description.getText().toString();

        this.btnAdd = (Button) findViewById(R.id.button);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = AddActivity.this.name.getText().toString();
                String des = AddActivity.this.description.getText().toString();
                Log.i("name", name);
                Log.i("des", des);
                if(name == "" || name.isEmpty() || des == "" || des.isEmpty()){
                    Toast.makeText(getApplicationContext(), "All fields must be filled in", Toast.LENGTH_SHORT).show();
                } else {
                    saveCharacter(name, des);
                }
            }
        });

        this.btnCancel = (Button) findViewById(R.id.button2);
        this.btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddActivity.super.finish();
            }
        });
    }

    private void saveCharacter(String name, String description) {
        getAllCharacters();
        if(!this.list.contains(name)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.vraagteken);
            contentValues.put(CharacterContract.CharacterEntry.COLUMN_NAME, name);
            contentValues.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, description);

            Uri uri = CharacterContract.CharacterEntry.CONTENT_URI;
            Uri uriRowInserted = getContentResolver().insert(uri, contentValues);
            Log.i(TAG, "Item inserted at: " + uriRowInserted);
            Toast.makeText(getApplicationContext(), "Inserted character " + name, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AddActivity.this.finish();
                }
            }, Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(getApplicationContext(), "Character already inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAllCharacters(){
        this.list = new ArrayList<>();
        String[] projection = {
                CharacterContract.CharacterEntry._ID,
                CharacterContract.CharacterEntry.COLUMN_PICTUREID,
                CharacterContract.CharacterEntry.COLUMN_NAME,
                CharacterContract.CharacterEntry.COLUMN_DESCRIPTION
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Uri uri = CharacterContract.CharacterEntry.CONTENT_URI;

        Log.i("uri", uri.toString());

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            try {
                while(cursor.moveToNext()) {
                    list.add(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME)));
                }
            } finally {
                cursor.close();
            }
        }

    }
}
