package com.rmuhamed.sample.poketest.ui.main

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.TemplatePokeTestApplication
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.IntentConstants.POKEMON
import com.rmuhamed.sample.poketest.ui.ViewModelCreator
import com.rmuhamed.sample.poketest.ui.ViewState
import com.rmuhamed.sample.poketest.ui.detail.PokeDetailActivity
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackAdapter
import com.rmuhamed.sample.poketest.ui.main.adapters.MyBackpackItemViewHandler
import kotlinx.android.synthetic.main.my_backpack_fragment.*


class MyBackpackFragment : Fragment(R.layout.my_backpack_fragment) {

    private val viewModel by viewModels<MyBackpackViewModel> {
        ViewModelCreator(app.repository)
    }

    private lateinit var app: TemplatePokeTestApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)

        app = requireNotNull(activity).application as TemplatePokeTestApplication

        viewModel.allInBackpack()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                ViewState.LOADING -> loading.show()
                ViewState.LOADED -> {
                    loading.hide()
                    my_pokemons.visibility = View.VISIBLE
                }
                else -> loading.hide()
            }
        })

        viewModel.inMyBackpack.observe(viewLifecycleOwner, Observer {
            my_pokemons.apply {
                adapter = MyBackpackAdapter(it, object : MyBackpackItemViewHandler {
                    override fun onPokemonSelected(pokemon: Pokemon) {
                        showDetailsOf(pokemon)
                    }
                })
                setHasFixedSize(true)
            }
        })
    }

    private fun showDetailsOf(pokemon: Pokemon) {
        this.startActivity(Intent().apply {
            setClass(requireContext(), PokeDetailActivity::class.java)
            putExtra(POKEMON, pokemon)
        }, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
    }

    companion object {
        fun newInstance() = MyBackpackFragment()
    }
}
