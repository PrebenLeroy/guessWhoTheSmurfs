package com.example.guesswhothesmurfs.adapters.ViewHolders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guesswhothesmurfs.R;
import com.example.guesswhothesmurfs.models.GuessWhoTheSmurfsCharacter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuessWhoTheSmurfsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cardView)
    CardView cv;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.characterImage)
    ImageView characterImage;



    public GuessWhoTheSmurfsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData( GuessWhoTheSmurfsCharacter character){

        name.setText(character.getName());
        description.setText(character.getDescription());
        characterImage.setImageResource(character.getCharacterImage());
        Context context = characterImage.getContext();
        Picasso.with(context).load(character.getCharacterImage()).resize(400, 400)
                .centerCrop().into(characterImage);

    }
}
