package com.rmuhamed.sample.poketest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.IntentConstants.POKEMON
import com.rmuhamed.sample.poketest.ui.view.paintFrom
import kotlinx.android.synthetic.main.activity_poke_detail.*
import java.util.*

class PokeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_detail)

        val pokemon = intent.getParcelableExtra<Pokemon>(POKEMON)

        setSupportActionBar(toolbar)

        collapsing_toolbar.apply {
            title = pokemon?.name ?: context.getString(R.string.no_name_placeholder)
            setCollapsedTitleTextColor(ContextCompat.getColor(this@PokeDetailActivity, R.color.white))
            setExpandedTitleColor(ContextCompat.getColor(this@PokeDetailActivity, R.color.white))
        }

        paint(pokemon)
    }

    private fun paint(pokemon: Pokemon?) {
        pokemon?.let {
            pokemon_picture_image.paintFrom(it.picture)
            pokemon_type_label.text = getString(R.string.type_placeholder, pokemon.type)
            pokemon_base_experience_label.text =
                getString(R.string.base_experience_placeholder, pokemon.baseExperience)
            pokemon_height_label.text = getString(R.string.height_placeholder, pokemon.height)
            pokemon_weight_label.text = getString(R.string.weight_placeholder, pokemon.weight)
            pokemon_capturedat_label.text = getString(R.string.captured_at_placeholder, pokemon.capturedAtStr)
        }
    }
}
