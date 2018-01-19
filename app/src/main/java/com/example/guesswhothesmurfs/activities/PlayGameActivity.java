package com.example.guesswhothesmurfs.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.fragments.DetailFragment;
import com.example.guesswhothesmurfs.fragments.PlayFragment;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;

/**
 * Created by prebe on 17/01/2018.
 */

public class PlayGameActivity extends FragmentActivity /*implements PlayFragment.OnCharacterSelectedListener*/{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        GuessWhoTheSmurfsCharacter character = (GuessWhoTheSmurfsCharacter) getIntent().getSerializableExtra("character");

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            FragmentManager manager = getSupportFragmentManager();
            if (manager.findFragmentById(R.id.fragment_container)==null) {
                // Create an instance of ExampleFragment
                PlayFragment firstFragment = new PlayFragment();

                // Add the fragment to the 'fragment_container' FrameLayout
                manager.beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }
    }

//
//    @Override
//    public void onCharacterSelected(int position) {
//
//        // The user selected the headline of an article from the HeadlinesFragment
//        // Capture the article fragment from the activity layout
//        DetailFragment detailFragment = (DetailFragment)
//                getSupportFragmentManager().findFragmentById(R.id.detailFragment);
//
//        if (detailFragment != null) {
//            // If article frag is available, we're in two-pane layout...
//
//            // Call a method in the ArticleFragment to update its content
//            detailFragment.updateDetailView(position);
//
//        } else {
//            // If the frag is not available, we're in the one-pane layout and must swap frags...
//
//            // Create fragment and give it an argument for the selected article
//            DetailFragment newFragment = new DetailFragment();
//            Bundle args = new Bundle();
//            args.putInt(DetailFragment.ARG_POSITION, position);
//            newFragment.setArguments(args);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            // Replace whatever is in the fragment_container view with this fragment,
//            // and add the transaction to the back stack so the user can navigate back
//            transaction.replace(R.id.fragment_container, newFragment);
//            transaction.addToBackStack(null);
//
//            // Commit the transaction
//            transaction.commit();
//        }
//    }
}
