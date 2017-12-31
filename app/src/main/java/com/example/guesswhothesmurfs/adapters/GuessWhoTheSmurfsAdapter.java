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

    @Override
    public GuessWhoTheSmurfsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
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
     *  Insert a new item to the RecyclerView on a predefined position
     * @param position The position to insert the new data
     * @param data The data to insert
     */
    public void insert(int position, GuessWhoTheSmurfsCharacter data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    /**
     *  Remove a RecyclerView item containing a specified Data object
     * @param position The pôstition of the data to remove
     */
    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
