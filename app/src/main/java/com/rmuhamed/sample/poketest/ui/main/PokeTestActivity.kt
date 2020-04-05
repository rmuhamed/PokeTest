package com.rmuhamed.sample.poketest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.rmuhamed.sample.poketest.R
import kotlinx.android.synthetic.main.activity_poke_test.*

class PokeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_poke_test)
        this.setSupportActionBar(toolbar)

        navigation_container.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search_and_catch -> {
                    load(CatchThemAllFragment.newInstance(), "CatchThemAll")
                    true
                }
                R.id.my_backpack -> {
                    load(MyBackpackFragment.newInstance(), "MyBackPack")
                    true
                }
                else -> false
            }
        }
    }

    private fun load(fragmentInstance: Fragment, tag: String) {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
            add(R.id.fragment_container, fragmentInstance, tag)
        }
    }
}
