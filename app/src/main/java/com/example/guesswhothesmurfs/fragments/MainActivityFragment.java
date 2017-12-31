package com.example.guesswhothesmurfs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guesswhothesmurfs.adapters.GuessWhoTheSmurfsAdapter;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;

import java.util.ArrayList;
import java.util.List;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.interfaces.RecyclerViewItemClickListener;
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

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                adapter.remove(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private List<GuessWhoTheSmurfsCharacter> generateData() {
        List<GuessWhoTheSmurfsCharacter> data = new ArrayList<>();

        for (int i = 0; i < 10; i++){

            GuessWhoTheSmurfsCharacter evilMorty = new GuessWhoTheSmurfsCharacter(R.mipmap.evilmorty, getString(R.string.evilmorty), getString(R.string.evilmortydescription));

            GuessWhoTheSmurfsCharacter rick = new GuessWhoTheSmurfsCharacter(R.mipmap.ricksanchez, getString(R.string.ricksanchez),
                getString(R.string.rickdescription));

            GuessWhoTheSmurfsCharacter morty = new GuessWhoTheSmurfsCharacter(R.mipmap.morty, getString(R.string.morty),
                getString(R.string.mortydescription));

            GuessWhoTheSmurfsCharacter buthole = new GuessWhoTheSmurfsCharacter(R.mipmap.mrpoopybutthole, getString(R.string.poopy),
                getString(R.string.pooptydescription));

            GuessWhoTheSmurfsCharacter meeseeks = new GuessWhoTheSmurfsCharacter(R.mipmap.meeseeks, getString(R.string.meeseeks),
                getString(R.string.meeseeksdescription));


        data.add(morty);
        data.add(rick);
        data.add(buthole);
        data.add(meeseeks);
        data.add(evilMorty);
    }

        return data;
    }
}
