package com.example.guesswhothesmurfs.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.adapters.ViewHolders.GuessWhoTheSmurfsViewHolder;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;
import com.example.guesswhothesmurfs.persistency.CharacterContract;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

public class GuessWhoTheSmurfsAdapter extends RecyclerView.Adapter<GuessWhoTheSmurfsViewHolder> {

    /**
     * Data class containing the characters which are shown in the list
     */
    List<GuessWhoTheSmurfsCharacter> list = Collections.emptyList();


    public GuessWhoTheSmurfsAdapter(List<GuessWhoTheSmurfsCharacter> list, Context context) {
        this.list = list;
        this.context = context;
    }


    /**
     * Reference to our activity
     */
    private Context context;

    private ViewGroup parent;

    @Override
    public GuessWhoTheSmurfsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(this.parent.getContext()).inflate(R.layout.row_layout, parent, false);
        GuessWhoTheSmurfsViewHolder holder = new GuessWhoTheSmurfsViewHolder(view);
        Log.d(this.getClass().getSimpleName(), "Creating viewholder");
        return holder;

    }

    @Override
    public void onBindViewHolder(GuessWhoTheSmurfsViewHolder holder, int position) {
        Log.d(this.getClass().getSimpleName(), "Binding position " + position);
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    /**
     *  Remove a RecyclerView item containing a specified Data object
     * @param position The pôstition of the data to remove
     */
    public GuessWhoTheSmurfsCharacter view(int position) {
        final GuessWhoTheSmurfsCharacter character = list.get(position);
        return character;
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
