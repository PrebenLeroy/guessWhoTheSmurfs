package com.example.guesswhothesmurfs.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guesswhothesmurfs.adapters.GuessWhoTheSmurfsAdapter;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;

import java.util.ArrayList;
import java.util.List;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.interfaces.RecyclerViewItemClickListener;
import com.example.guesswhothesmurfs.persistency.CharacterContract;
import com.example.guesswhothesmurfs.util.CustomRVItemTouchListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final GuessWhoTheSmurfsAdapter adapter = new GuessWhoTheSmurfsAdapter(generateData(),getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        /*recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                adapter.remove(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
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
}
