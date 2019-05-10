package com.rmuhamed.sample.poketest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rmuhamed.sample.poketest.R

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
