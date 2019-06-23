package com.rmuhamed.sample.poketest.ui.view

import android.widget.ImageView
import com.rmuhamed.sample.poketest.R
import com.squareup.picasso.Picasso

fun ImageView.paintFrom(path: String, placeHolderResId: Int = 0, errorResId: Int = 0) {
    Picasso.get()
        .load(path)
        .placeholder(if (placeHolderResId > 0) placeHolderResId else R.drawable.ic_pokedex_placeholder)
        .error(if (errorResId > 0) errorResId else R.drawable.ic_sleeping)
        .into(this)
}