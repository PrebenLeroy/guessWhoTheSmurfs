package com.example.guesswhothesmurfs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.adapters.ViewHolders.GuessWhoTheSmurfsViewHolder;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;

import java.util.Collections;
import java.util.List;

/**
 * Created by prebe on 19/01/2018.
 */

public class GameAdapter extends RecyclerView.Adapter<GuessWhoTheSmurfsViewHolder>  {
    /**
     * Data class containing the characters which are shown in the list
     */
    List<GuessWhoTheSmurfsCharacter> list = Collections.emptyList();


    public GameAdapter (List<GuessWhoTheSmurfsCharacter> list, Context context) {
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

    public List<GuessWhoTheSmurfsCharacter> getAllCharacters(){
        return list;
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
