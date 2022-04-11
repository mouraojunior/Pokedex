package juniormourao.pokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import juniormourao.pokedex.R
import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.databinding.PokemonListItemBinding
import juniormourao.pokedex.util.PokemonUtil.capitalizeFirstChar
import timber.log.Timber

class PokemonListAdapter :
    ListAdapter<PokemonDetailResponseDto, PokemonListAdapter.PokemonListViewHolder>(PokemonDiffUtil()) {

    inner class PokemonListViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPokemon(pokemon: PokemonDetailResponseDto) {
            binding.apply {
                setPokemonTypes(pokemon)
                tvPokemonName.text = pokemon.name.capitalizeFirstChar()
                tvPokemonId.text = getPokemonId(pokemon.id)
                imgPokemon.load(pokemon.sprites.other.officialArtwork.frontDefault) {
                    crossfade(true)
                }
            }
        }

        private fun setPokemonTypes(pokemon: PokemonDetailResponseDto) {
            binding.apply {
                Timber.e("${pokemon.name} - ${pokemon.types}")
                pokemon.types.forEach { pokemonType ->
                    var typeColor = 0
                    var backgroundTypeColor = 0
                    val pokemonTypeSlot = pokemonType.slot
                    var pokemonTypeIcon = 0
                    when (pokemonType.type.name) {
                        "bug" -> {
                            backgroundTypeColor = R.color.background_type_bug
                            typeColor = R.color.type_bug
                            pokemonTypeIcon = R.drawable.ic_type_bug
                        }
                        "dark" -> {
                            backgroundTypeColor = R.color.background_type_dark
                            typeColor = R.color.type_dark
                            pokemonTypeIcon = R.drawable.ic_type_dark
                        }
                        "dragon" -> {
                            backgroundTypeColor = R.color.background_type_dragon
                            typeColor = R.color.type_dragon
                            pokemonTypeIcon = R.drawable.ic_type_dragon
                        }
                        "electric" -> {
                            backgroundTypeColor = R.color.background_type_electric
                            typeColor = R.color.type_electric
                            pokemonTypeIcon = R.drawable.ic_type_electric
                        }
                        "fairy" -> {
                            backgroundTypeColor = R.color.background_type_fairy
                            typeColor = R.color.type_fairy
                            pokemonTypeIcon = R.drawable.ic_type_fairy
                        }
                        "fighting" -> {
                            backgroundTypeColor = R.color.background_type_fighting
                            typeColor = R.color.type_fighting
                            pokemonTypeIcon = R.drawable.ic_type_fighting
                        }
                        "fire" -> {
                            backgroundTypeColor = R.color.background_type_fire
                            typeColor = R.color.type_fire
                            pokemonTypeIcon = R.drawable.ic_type_fire
                        }
                        "flying" -> {
                            backgroundTypeColor = R.color.background_type_flying
                            typeColor = R.color.type_flying
                            pokemonTypeIcon = R.drawable.ic_type_flying
                        }
                        "ghost" -> {
                            backgroundTypeColor = R.color.background_type_ghost
                            typeColor = R.color.type_ghost
                            pokemonTypeIcon = R.drawable.ic_type_ghost
                        }
                        "grass" -> {
                            backgroundTypeColor = R.color.background_type_grass
                            typeColor = R.color.type_grass
                            pokemonTypeIcon = R.drawable.ic_type_grass
                        }
                        "ground" -> {
                            backgroundTypeColor = R.color.background_type_ground
                            typeColor = R.color.type_ground
                            pokemonTypeIcon = R.drawable.ic_type_ground
                        }
                        "ice" -> {
                            backgroundTypeColor = R.color.background_type_ice
                            typeColor = R.color.type_ice
                            pokemonTypeIcon = R.drawable.ic_type_ice
                        }
                        "normal" -> {
                            backgroundTypeColor = R.color.background_type_normal
                            typeColor = R.color.type_normal
                            pokemonTypeIcon = R.drawable.ic_type_normal
                        }
                        "poison" -> {
                            backgroundTypeColor = R.color.background_type_poison
                            typeColor = R.color.type_poison
                            pokemonTypeIcon = R.drawable.ic_type_poison
                        }
                        "psychic" -> {
                            backgroundTypeColor = R.color.background_type_psychic
                            typeColor = R.color.type_psychic
                            pokemonTypeIcon = R.drawable.ic_type_psychic
                        }
                        "rock" -> {
                            backgroundTypeColor = R.color.background_type_rock
                            typeColor = R.color.type_rock
                            pokemonTypeIcon = R.drawable.ic_type_rock
                        }
                        "steel" -> {
                            backgroundTypeColor = R.color.background_type_steel
                            typeColor = R.color.type_steel
                            pokemonTypeIcon = R.drawable.ic_type_steel
                        }
                        "water" -> {
                            backgroundTypeColor = R.color.background_type_water
                            typeColor = R.color.type_water
                            pokemonTypeIcon = R.drawable.ic_type_water
                        }
                    }
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

        private fun getPokemonId(pokemonId: Int): String {
            return "#${pokemonId.toString().padStart(3, '0')}"
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

    class PokemonDiffUtil : DiffUtil.ItemCallback<PokemonDetailResponseDto>() {
        override fun areItemsTheSame(
            oldItem: PokemonDetailResponseDto,
            newItem: PokemonDetailResponseDto,
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: PokemonDetailResponseDto,
            newItem: PokemonDetailResponseDto,
        ) = oldItem.name == newItem.name
    }
}