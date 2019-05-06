package com.rmuhamed.sample.poketest.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R

class PokeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_detail)

        viewModel = ViewModelProviders.of(this).get(PokeDetailViewModel::class.java)
    }
}
