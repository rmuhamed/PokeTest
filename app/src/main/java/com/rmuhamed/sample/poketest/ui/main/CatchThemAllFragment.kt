package com.rmuhamed.sample.poketest.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.TemplatePokeTestApplication
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.ViewModelCreator
import com.rmuhamed.sample.poketest.ui.view.from
import kotlinx.android.synthetic.main.catch_them_all_fragment.*

open class CatchThemAllFragment : Fragment(R.layout.catch_them_all_fragment) {

    private val viewModel by viewModels<CatchThemAllViewModel> {
        ViewModelCreator(app.repository)
    }

    private lateinit var app: TemplatePokeTestApplication

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skip_it_button.setOnClickListener { viewModel.letsFindAnyPokemon() }
        catch_it_button.setOnClickListener { buttonView ->
            viewModel.catchPokemon()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        app = requireActivity().application as TemplatePokeTestApplication

        viewModel.letsFindAnyPokemon()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pokemonObservable.observe(viewLifecycleOwner, Observer { pokemon ->
            loading.visibility = View.INVISIBLE
            skip_it_button.visibility = View.VISIBLE

            pokemon_card_view.visibility = View.VISIBLE
            pokemon_card_view.from(pokemon)

            catch_it_button.tag = pokemon
            viewModel.checkIfPokemonIsInBackpack()
        })

        viewModel.canWeCatchIt.observe(viewLifecycleOwner, Observer {
            catch_it_button.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.caughtPokemon.observe(viewLifecycleOwner, Observer {
            catch_it_button.visibility = View.GONE
            viewModel.letsFindAnyPokemon()
        })
    }

    companion object {
        fun newInstance() = CatchThemAllFragment()
    }
}
