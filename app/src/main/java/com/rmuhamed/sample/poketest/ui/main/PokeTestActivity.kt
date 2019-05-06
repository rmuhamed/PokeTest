package com.rmuhamed.sample.poketest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.config.RestApiConfigurator
import com.rmuhamed.sample.poketest.config.RestApiDefinition
import com.rmuhamed.sample.poketest.data.AsyncResult
import com.rmuhamed.sample.poketest.data.RestApiRepository
import com.rmuhamed.sample.poketest.data.dto.PokemonResponseDTO
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.android.synthetic.main.activity_poke_test.*
import java.lang.Error

class PokeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_test)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CatchThemAllFragment.newInstance(), "CatchThemAll")
                .commit()
    }
}
