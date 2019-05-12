package com.rmuhamed.sample.poketest.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class MyBackpackAdapter(val pokemons: List<Pokemon>?) :
    RecyclerView.Adapter<MyBackpackAdapter.MyBackpackItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBackpackItemViewHolder {
        return MyBackpackItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemons?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyBackpackItemViewHolder, position: Int) {
        pokemons?.let {
            val pokemonAt = pokemons[position]

            holder.itemView.pokemon_type_label.text = pokemonAt.type
            holder.itemView.pokemon_base_experience_label.text = pokemonAt.baseExperience
            holder.itemView.pokemon_name_label.text = pokemonAt.name
            holder.itemView.pokemon_height_label.text = pokemonAt.height
            holder.itemView.pokemon_weight_label.text = pokemonAt.weight
            holder.itemView.pokemon_capturedat_label.text = pokemonAt.capturedAt.toString()
        }
    }

    class MyBackpackItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
