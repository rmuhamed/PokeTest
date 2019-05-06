package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rmuhamed.sample.poketest.R
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
        viewModel = ViewModelProviders.of(this).get(CatchThemAllViewModel::class.java)

        viewModel.observable.observe(this, Observer {
            poke_info_container.visibility = View.VISIBLE
            loading.visibility = View.INVISIBLE

            pokemon_name.text = it.name
            pokemon_height.text = it.height
            pokemon_weight.text = it.weight
        })
    }

}
