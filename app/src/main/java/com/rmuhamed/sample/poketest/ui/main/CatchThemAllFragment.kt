package com.rmuhamed.sample.poketest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.data.RepositoryFactory
import com.rmuhamed.sample.poketest.ui.CustomViewModelProvider
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, CustomViewModelProvider(RepositoryFactory.create(RepositoryFactory.Type.NETWORK)))
                .get(CatchThemAllViewModel::class.java)

        viewModel.observable.observe(this, Observer {
            poke_info_container.visibility = View.VISIBLE
            loading.visibility = View.INVISIBLE

            Picasso.get().load(it.picture)
                    .placeholder(R.drawable.ic_pokedex_placeholder)
                    .into(pokemon_picture)

            pokemon_name.text = it.name
            pokemon_height.text = getString(R.string.height_placeholder, it.height)
            pokemon_weight.text = getString(R.string.weight_placeholder, it.weight)
            pokemon_type.text = getString(R.string.type_placeholder, it.type)
            pokemon_base_experience.text = getString(R.string.base_experience_placeholder, it.baseExperience)
        })
    }

}
