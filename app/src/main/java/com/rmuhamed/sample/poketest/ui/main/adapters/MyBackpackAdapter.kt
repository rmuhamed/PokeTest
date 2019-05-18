package com.rmuhamed.sample.poketest.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class MyBackpackAdapter(private val pokemons: List<Pokemon>?, private val itemViewHandler: MyBackpackItemViewHandler) :
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

            holder.paintWith(pokemonAt)
            //TODO: RM - WRAP Into customView, to not expose Library usage
            Picasso.get().load(pokemonAt.picture)
                .placeholder(R.drawable.ic_pikachu_back)
                .into(holder.itemView.pokemon_picture_image)

            holder.itemView.setOnClickListener {
                itemViewHandler.onPokemonSelected(
                    pokemonAt,
                    holder.itemView.pokemon_picture_image
                )
            }
        }
    }

    class MyBackpackItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun paintWith(pokemon: Pokemon) {
            view.pokemon_name_label.text = pokemon.name
            view.pokemon_type_label.text = view.context.getString(R.string.type_placeholder, pokemon.type)
            view.pokemon_base_experience_label.text =
                view.context.getString(R.string.base_experience_placeholder, pokemon.baseExperience)
            view.pokemon_height_label.text = view.context.getString(R.string.height_placeholder, pokemon.height)
            view.pokemon_weight_label.text = view.context.getString(R.string.weight_placeholder, pokemon.weight)
            view.pokemon_capturedat_label.text =
                view.context.getString(R.string.captured_at_placeholder, pokemon.capturedAtStr)
        }
    }
}
