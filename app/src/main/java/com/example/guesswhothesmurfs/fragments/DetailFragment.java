package com.example.guesswhothesmurfs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guesswhothesmurfs.R;

/**
 * Created by prebe on 17/01/2018.
 */

public class DetailFragment extends Fragment {

    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
//    ImageView picture;
    TextView character, description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.


        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.detail_activity, container, false);
//        picture = (ImageView) v.findViewById(R.id.image_fragment);
        character = (TextView) v.findViewById(R.id.characterFragment);
        description = (TextView) v.findViewById(R.id.descriptionFragment);
        if (savedInstanceState != null) {
//            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
//            picture.setImageResource(savedInstanceState.getInt("picID"));
            character.setText(savedInstanceState.getString("name"));
            description.setText(savedInstanceState.getString("description"));
        }
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateDetailView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateDetailView(mCurrentPosition);
        }
    }

    public void updateDetailView(int position) {
//        picture.setImageResource(R.mipmap.bril);
        character.setText("test");
        description.setText("test");
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
//        outState.putInt("picId", R.mipmap.bril);
        outState.putString("name", character.getText().toString());
        outState.putString("description", description.getText().toString());

    }

}
