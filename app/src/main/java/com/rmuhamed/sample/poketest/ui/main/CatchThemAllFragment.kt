package com.rmuhamed.sample.poketest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
import com.rmuhamed.sample.poketest.ui.PokeTestApplication
import kotlinx.android.synthetic.main.catch_them_all_fragment.*

class CatchThemAllFragment : Fragment() {

    companion object {
        fun newInstance() = CatchThemAllFragment()
    }

    private lateinit var viewModel: CatchThemAllViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.catch_them_all_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skip_it_button.setOnClickListener { viewModel.letsFindPokemon() }
        catch_it_button.setOnClickListener { buttonView -> viewModel.catchPokemon(buttonView.tag as Pokemon) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = activity!!.application as PokeTestApplication

        viewModel = ViewModelProviders
            .of(this, CustomViewModelProvider(application.networkRepository, application.persistenceRepository))
            .get(CatchThemAllViewModel::class.java)

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
            viewModel.letsFindPokemon()
        })
    }
}
