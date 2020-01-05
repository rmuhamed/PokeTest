package com.rmuhamed.sample.poketest.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
import com.rmuhamed.sample.poketest.ui.IntentConstants.POKEMON
import com.rmuhamed.sample.poketest.ui.PokeTestApplication
import com.rmuhamed.sample.poketest.ui.detail.PokeDetailActivity
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackAdapter
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackItemViewHandler
import kotlinx.android.synthetic.main.my_backpack_fragment.*


class MyBackpackFragment : Fragment(R.layout.my_backpack_fragment) {

    private lateinit var app: PokeTestApplication
    private lateinit var viewModel: MyBackpackViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        app = requireNotNull(activity).application as PokeTestApplication

        viewModel = initViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.myPokemonsObservable.observe(this, Observer {
            progress.visibility = View.GONE
            my_pokemons.visibility = View.VISIBLE

            my_pokemons.apply {
                adapter = MyBackpackAdapter(it, object : MyBackpackItemViewHandler {
                    override fun onPokemonSelected(pokemon: Pokemon, pokemonImage: AppCompatImageView) {
                        showDetailsOf(pokemon, pokemonImage)
                    }
                })
                setHasFixedSize(true)
            }
        })
    }

    private fun showDetailsOf(
        pokemon: Pokemon,
        pokemonImage: AppCompatImageView
    ) {

        //val options = ActivityOptionsCompat
        // .makeSceneTransitionAnimation(this.activity as Activity, pokemonImage, "Image")

        this.startActivity(Intent().apply {
            setClass(this@MyBackpackFragment.context!!, PokeDetailActivity::class.java)
            putExtra(POKEMON, pokemon)
        })
    }

    private fun initViewModel(): MyBackpackViewModel {
        val customVMProvider = CustomViewModelProvider(
            app.networkRepository,
            app.persistenceRepository
        )

        return ViewModelProviders
            .of(this, customVMProvider)
            .get(MyBackpackViewModel::class.java)
    }

    companion object {
        fun newInstance() = MyBackpackFragment()
    }
}
