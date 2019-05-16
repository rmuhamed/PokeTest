package com.rmuhamed.sample.poketest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.android.synthetic.main.activity_poke_detail.*

class PokeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_detail)

        val pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        setSupportActionBar(toolbar)

        collapsing_toolbar?.title = pokemon.name

        viewModel = ViewModelProviders.of(this).get(PokeDetailViewModel::class.java)
    }
}
