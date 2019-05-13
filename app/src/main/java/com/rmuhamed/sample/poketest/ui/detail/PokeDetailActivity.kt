package com.rmuhamed.sample.poketest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.*

class PokeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_detail)

        val pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        viewModel = ViewModelProviders.of(this).get(PokeDetailViewModel::class.java)

        pokemon_height_label.text = pokemon.height
        pokemon_weight_label.text = pokemon.weight
        pokemon_base_experience_label.text = pokemon.baseExperience
    }
}
