package com.rmuhamed.sample.poketest.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_card_view.view.*

class PokemonCardView : CardView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.pokemon_card_view, this, true)
    }

    fun from(pokemon: Pokemon) {

        pokemon.also {
            pokemon_name_label.text = it.name
            pokemon_height_label.text = context.getString(R.string.height_placeholder, it.height)
            pokemon_weight_label.text = context.getString(R.string.weight_placeholder, it.weight)
            pokemon_type_label.text = context.getString(R.string.type_placeholder, it.type)
            pokemon_base_exp_label.text = context.getString(R.string.base_experience_placeholder, it.baseExperience)

            if (it.picture.isNotBlank()) {
                pokemon_picture_image.paintFrom(it.picture)
            } else {
                pokemon_picture_image.setImageResource(R.drawable.ic_pokedex_placeholder)
            }
        }
    }
}