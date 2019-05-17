package com.rmuhamed.sample.poketest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_poke_detail.*

class PokeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_detail)

        val pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        setSupportActionBar(toolbar)

        collapsing_toolbar?.title = pokemon?.name ?: "NULL NAME"

        paint(pokemon)

        viewModel = ViewModelProviders.of(this).get(PokeDetailViewModel::class.java)
    }

    private fun paint(pokemon: Pokemon?) {
        pokemon?.let {
            Picasso.get().load(pokemon.picture)
                .placeholder(R.drawable.ic_pokedex_placeholder)
                .into(pokemon_picture_image)
            pokemon_type_label.text = getString(R.string.type_placeholder, pokemon.type)
            pokemon_base_experience_label.text =
                getString(R.string.base_experience_placeholder, pokemon.baseExperience)
            pokemon_height_label.text = getString(R.string.height_placeholder, pokemon.height)
            pokemon_weight_label.text = getString(R.string.weight_placeholder, pokemon.weight)
            pokemon_capturedat_label.text = getString(R.string.captured_at_placeholder, pokemon.capturedAtStr)
        }
    }
}
