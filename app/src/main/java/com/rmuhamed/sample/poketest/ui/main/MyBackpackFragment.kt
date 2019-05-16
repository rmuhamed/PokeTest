package com.rmuhamed.sample.poketest.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
import com.rmuhamed.sample.poketest.ui.PokeTestApplication
import com.rmuhamed.sample.poketest.ui.detail.PokeDetailActivity
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackAdapter
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackItemViewHandler
import kotlinx.android.synthetic.main.my_backpack_fragment.*


class MyBackpackFragment : Fragment() {

    companion object {
        fun newInstance() = MyBackpackFragment()
    }

    private lateinit var viewModel: MyBackpackViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_backpack_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = activity!!.application as PokeTestApplication

        viewModel = ViewModelProviders
            .of(this, CustomViewModelProvider(application.networkRepository, application.persistenceRepository))
            .get(MyBackpackViewModel::class.java)

        viewModel.myPokemonsObservable.observe(this, Observer {
            progress.visibility = View.GONE
            my_pokemons.visibility = View.VISIBLE
            my_pokemons.adapter = MyBackpackAdapter(it, object : MyBackpackItemViewHandler {
                override fun onPokemonSelected(pokemon: Pokemon, pokemonImage: AppCompatImageView) {
                    showDetailsOf(pokemon, pokemonImage)
                }
            })
            my_pokemons.setHasFixedSize(true)
        })

    }

    private fun showDetailsOf(
        pokemon: Pokemon,
        pokemonImage: AppCompatImageView
    ) {

        //val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity as Activity, pokemonImage, "Image")


        this.startActivity(Intent().apply {
            setClass(this@MyBackpackFragment.context, PokeDetailActivity::class.java)
            putExtra("pokemon", pokemon)
        })
    }

}
