package juniormourao.pokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import juniormourao.pokedex.databinding.PokemonListItemBinding
import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.util.PokemonUtil
import juniormourao.pokedex.util.PokemonUtil.capitalizeFirstChar
import juniormourao.pokedex.util.PokemonUtil.getPokemonId

class PokemonListAdapter(
    private val onItemClick: (Pokemon) -> Unit,
) :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonListViewHolder>(PokemonDiffUtil()) {
    inner class PokemonListViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPokemon(pokemon: Pokemon) {
            binding.apply {
                setPokemonTypes(pokemon)
                tvPokemonName.text = pokemon.name.capitalizeFirstChar()
                tvPokemonId.text = getPokemonId(pokemon.id)
                imgPokemon.load(pokemon.image)

                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) onItemClick(getItem(adapterPosition))
                }
            }
        }

        private fun setPokemonTypes(pokemon: Pokemon) {
            binding.apply {
                pokemon.types.forEach { pokemonType ->
                    val pokemonTypeSlot = pokemonType.slot
                    val pokemonResources =
                        PokemonUtil.getPokemonResourcesByType(pokemonType.type.name)
                    val backgroundTypeColor = pokemonResources.first
                    val typeColor = pokemonResources.second
                    val pokemonTypeIcon = pokemonResources.third

                    when (pokemonTypeSlot) {
                        1 -> {
                            imgPokemonType1.setImageResource(pokemonTypeIcon)
                            tvPokemonType1.text = pokemonType.type.name.capitalizeFirstChar()
                            cvPokemonType1.setCardBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    typeColor
                                )
                            )
                            cvRoot.setCardBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    backgroundTypeColor
                                )
                            )
                        }
                        2 -> {
                            imgPokemonType2.setImageResource(pokemonTypeIcon)
                            tvPokemonType2.text = pokemonType.type.name.capitalizeFirstChar()
                            cvPokemonType2.setCardBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    typeColor
                                )
                            )
                        }
                    }
                }
                if (pokemon.types.size == 1) cvPokemonType2.visibility = View.GONE
                else cvPokemonType2.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            PokemonListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bindPokemon(getItem(position))
    }

    class PokemonDiffUtil : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon,
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon,
        ) = oldItem.name == newItem.name
    }
}