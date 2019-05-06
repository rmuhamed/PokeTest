package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rmuhamed.sample.poketest.R

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
        viewModel = ViewModelProviders.of(this).get(MyBackpackViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
