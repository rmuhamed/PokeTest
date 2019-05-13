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
import com.squareup.picasso.Picasso
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

        skip_it_button.setOnClickListener { viewModel.searchForAnotherPokemon() }
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

            poke_info_container.visibility = View.VISIBLE
            skip_it_button.visibility = View.VISIBLE

            //TODO: RM - WRAP Into customView, to not expose Library usage
            Picasso.get().load(it.picture)
                .placeholder(R.drawable.ic_pokedex_placeholder)
                .into(pokemon_picture_image)

            pokemon_name_label.text = it.name
            pokemon_height_label.text = getString(R.string.height_placeholder, it.height)
            pokemon_weight_label.text = getString(R.string.weight_placeholder, it.weight)
            pokemon_type_label.text = getString(R.string.type_placeholder, it.type)
            pokemon_base_experience_label.text = getString(R.string.base_experience_placeholder, it.baseExperience)

            catch_it_button.tag = it
        })

        viewModel.catchItButtonObservable.observe(this, Observer {
            catch_it_button.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

}
