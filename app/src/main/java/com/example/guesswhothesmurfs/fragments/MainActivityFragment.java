package com.example.guesswhothesmurfs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guesswhothesmurfs.activities.MainActivity;
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
    private static final String TAG = "GuessWhoTheSmurfs";
    private GuessWhoTheSmurfsCharacter character;

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

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, final int position) {

                character = adapter.view(position);

                final LayoutInflater inflater = LayoutInflater.from(getContext());
                final View dialog = inflater.inflate(R.layout.detail_dialog, null);
                final View updateView = inflater.inflate(R.layout.update_character, null);
                AlertDialog alert = new AlertDialog.Builder(getContext())
                        .setView(dialog).setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String selection = CharacterContract.CharacterEntry.COLUMN_NAME + " = ?";
                                String[] selectionArgs = {MainActivityFragment.this.character.getName()};

                                Uri uri = Uri.withAppendedPath(CharacterContract.CharacterEntry.CONTENT_URI, character.getName());
                                int rowsUpdated = getContext().getContentResolver().delete(uri, selection, selectionArgs);
                                Log.i(TAG, "Number of rows updated: " + rowsUpdated);

                                Toast.makeText(getContext(), "Deleted " + character.getName(), Toast.LENGTH_SHORT).show();

                                adapter.remove(position);
                            }
                        }).setNegativeButton("Update", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                final AlertDialog update = new AlertDialog.Builder(getContext())
                                        .setView(updateView).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {

                                                EditText updateName = (EditText) updateView.findViewById(R.id.newName);
                                                EditText updateDescription = (EditText) updateView.findViewById(R.id.newDescription);

                                                if(updateName.getText().toString().length() <= 0 || updateDescription.getText().toString().length() <= 0) {

                                                    Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    String name = updateName.getText().toString();
                                                    String description = updateDescription.getText().toString();

                                                    String selection = CharacterContract.CharacterEntry.COLUMN_NAME + " = ?";
                                                    String[] selectionArgs = {character.getName()};

                                                    ContentValues cv = new ContentValues();
                                                    cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, name);
                                                    cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, description);

                                                    Uri uri = CharacterContract.CharacterEntry.CONTENT_URI;
                                                    int rowsUpdated = getContext().getContentResolver().update(uri, cv, selection, selectionArgs);
                                                    Log.i(TAG, "Number of rows updated: " + rowsUpdated);

                                                    Toast.makeText(getContext(), "Updated " + character.getName(), Toast.LENGTH_SHORT).show();

                                                    adapter.notifyItemChanged(position);
                                                    adapter.notifyDataSetChanged();

                                                    getActivity().recreate();
                                                }
                                            }
                                        }).setNegativeButton(android.R.string.cancel, null).show();;

                                doKeepDialog(update);

                            }
                        }).setNeutralButton(android.R.string.cancel, null).show();

                doKeepDialog(alert);

                ImageView imageView = (ImageView) dialog.findViewById(R.id.dialog_image);
                imageView.setImageResource(character.getCharacterImage());
                TextView name = (TextView) dialog.findViewById(R.id.character);
                name.setText(character.getName());
                TextView description = (TextView) dialog.findViewById(R.id.description);
                description.setText(character.getDescription());

                EditText updateName = (EditText) updateView.findViewById(R.id.newName);
                updateName.setText(character.getName(), TextView.BufferType.EDITABLE);
                EditText updateDescription = (EditText) updateView.findViewById(R.id.newDescription);
                updateDescription.setText(character.getDescription(), TextView.BufferType.EDITABLE);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void doKeepDialog(Dialog dialog){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
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
