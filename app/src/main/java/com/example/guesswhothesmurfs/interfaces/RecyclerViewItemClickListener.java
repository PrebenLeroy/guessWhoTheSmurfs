package com.example.guesswhothesmurfs.interfaces;

import android.view.View;

/**
 * Created by eothein on 25.10.17.
 */

public interface RecyclerViewItemClickListener {

    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
