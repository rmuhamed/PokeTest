package com.rmuhamed.sample.poketest.ui.view

import android.widget.ImageView
import com.google.android.material.card.MaterialCardView
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catch_them_all_fragment.view.*

/**
 * Fill ImageView with imageResource from path or a default placeholder otherwise
 */
fun ImageView.paintFrom(path: String, placeHolderResId: Int = 0, errorResId: Int = 0) {
    if (path.isBlank()) {
        this.setImageResource(R.drawable.ic_pikachu_back)
    } else {
        Picasso.get()
            .load(path)
            .placeholder(if (placeHolderResId > 0) placeHolderResId else R.drawable.ic_pokedex_placeholder)
            .error(if (errorResId > 0) errorResId else R.drawable.ic_sleeping)
            .into(this)
    }
}

fun MaterialCardView.from(pokemon: Pokemon) {
    pokemon.also {
        pokemon_name_label.text = it.name
        pokemon_type_label.text = context.getString(R.string.type_placeholder, it.type)
        pokemon_base_exp_label.text = context.getString(R.string.base_experience_placeholder, it.baseExperience)
        pokemon_picture_image.paintFrom(it.picture)
    }
}