package com.rmuhamed.sample.poketest.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
import kotlinx.android.synthetic.main.catch_them_all_fragment.*

open class CatchThemAllFragment : Fragment(R.layout.catch_them_all_fragment) {

    companion object {
        fun newInstance() = CatchThemAllFragment()
    }

    private val viewModel: AbstractCatchThemAllViewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val configuration = getConfiguration()
        initViewModel(configuration)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skip_it_button.setOnClickListener { viewModel.letsFindAPokemon() }
        catch_it_button.setOnClickListener { buttonView -> viewModel.catchPokemon(buttonView.tag as Pokemon) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.letsFindAPokemon()

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
            viewModel.letsFindAPokemon()
        })
    }

    private fun initViewModel(appConfiguration: AppConfiguration): AbstractCatchThemAllViewModel {
        val customViewModelProvider = CustomViewModelProvider(
            appConfiguration.networkRepository,
            appConfiguration.persistenceRepository
        )

        return ViewModelProviders
            .of(this, customViewModelProvider)
            .get(CatchThemAllViewModel::class.java)

    }

    fun getConfiguration(): AppConfiguration = AppConfiguration.get()
}
