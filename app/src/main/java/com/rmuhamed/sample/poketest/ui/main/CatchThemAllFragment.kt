package com.rmuhamed.sample.poketest.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.TemplatePokeTestApplication
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
import kotlinx.android.synthetic.main.catch_them_all_fragment.*

open class CatchThemAllFragment : Fragment(R.layout.catch_them_all_fragment) {

    private lateinit var viewModel: CatchThemAllViewModel
    private lateinit var app: TemplatePokeTestApplication

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skip_it_button.setOnClickListener { viewModel.letsFindAnyPokemon() }
        catch_it_button.setOnClickListener { buttonView ->
            viewModel.catchPokemon(buttonView.tag as Pokemon)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        app = requireNotNull(activity).application as TemplatePokeTestApplication

        viewModel = initViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pokemonInfoObservable.observe(this, Observer {
            loading.visibility = View.INVISIBLE
            skip_it_button.visibility = View.VISIBLE

            pokemon_card_view.visibility = View.VISIBLE
            pokemon_card_view.from(it)

            catch_it_button.tag = it
        })

        viewModel.catchItButtonObservable.observe(this, Observer {
            catch_it_button.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.caughtPokemonObservable.observe(this, Observer {
            catch_it_button.visibility = View.GONE
            viewModel.letsFindAnyPokemon()
        })
    }

    private fun initViewModel(): CatchThemAllViewModel {
        val customViewModelProvider =
            CustomViewModelProvider(app.networkRepository, app.persistenceRepository)

        return ViewModelProviders
            .of(this, customViewModelProvider)
            .get(CatchThemAllViewModel::class.java)

    }

    companion object {
        fun newInstance() = CatchThemAllFragment()
    }
}
