package br.com.heiderlopes.demorx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.heiderlopes.demorx.databinding.PokemonItemBinding
import br.com.heiderlopes.demorx.model.Pokemon
import com.squareup.picasso.Picasso

class MainListAdapter(
    private val picasso: Picasso
) : RecyclerView.Adapter<MainListAdapter.MainViewHolder>() {

    private var pokemons = ArrayList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding =
            PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(picasso, itemBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pokemon: Pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size

    fun addItem(pokemon: Pokemon) {
        this.pokemons.add(pokemon)
    }

    class MainViewHolder(
        private val picasso: Picasso,
        private val itemBinding: PokemonItemBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pokemon: Pokemon) {
            itemBinding.tvPokemon.text = pokemon.name
            picasso.load(pokemon.sprites.frontDefault).into(itemBinding.ivPokemon)
        }
    }
}