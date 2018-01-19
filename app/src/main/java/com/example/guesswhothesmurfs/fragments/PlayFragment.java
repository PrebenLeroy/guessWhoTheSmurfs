package com.example.guesswhothesmurfs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.activities.GameChooseActivity;
import com.example.guesswhothesmurfs.adapters.GuessWhoTheSmurfsAdapter;
import com.example.guesswhothesmurfs.interfaces.RecyclerViewItemClickListener;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;
import com.example.guesswhothesmurfs.persistency.CharacterContract;
import com.example.guesswhothesmurfs.util.CustomRVItemTouchListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by prebe on 17/01/2018.
 */

public class PlayFragment extends Fragment {

    private RecyclerView recyclerView;
    private static final String TAG = "GuessWhoTheSmurfs";
    private GuessWhoTheSmurfsCharacter character;
    private GuessWhoTheSmurfsAdapter adapter;
    private List<GuessWhoTheSmurfsCharacter> characters;

    public PlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            GuessWhoTheSmurfsCharacter[] smurfsCharacters = (GuessWhoTheSmurfsCharacter[]) savedInstanceState.getSerializable("characterArray");
            characters = Arrays.asList(smurfsCharacters);
        } else {
            characters = generateData();
        }

        adapter = new GuessWhoTheSmurfsAdapter(characters,getActivity().getApplicationContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, final int position) {

                character = adapter.view(position);

            }
        }));
    }

    private List<GuessWhoTheSmurfsCharacter> generateData() {
        List<GuessWhoTheSmurfsCharacter> data = new ArrayList<>();

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

        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            try {
                while(cursor.moveToNext()) {
                    int picId = cursor.getInt(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_PICTUREID));
                    String name = cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME));
                    String description = cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION));
                    data.add(new GuessWhoTheSmurfsCharacter(picId, name, description));

                }
            } finally {
                cursor.close();
            }
        }

        return data;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<GuessWhoTheSmurfsCharacter> characterList = adapter.getAllCharacters();
        GuessWhoTheSmurfsCharacter[] guessWhoTheSmurfsCharacters = new GuessWhoTheSmurfsCharacter[characterList.size()];
        characterList.toArray(guessWhoTheSmurfsCharacters);
        outState.putSerializable("characterArray", guessWhoTheSmurfsCharacters);
    }

}
