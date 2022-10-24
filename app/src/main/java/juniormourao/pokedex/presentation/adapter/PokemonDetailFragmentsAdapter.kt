package juniormourao.pokedex.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.presentation.pokemon_details.about.PokemonDetailAboutFragment
import juniormourao.pokedex.presentation.pokemon_details.evolution.PokemonDetailEvolutionFragment
import juniormourao.pokedex.presentation.pokemon_details.stats.PokemonDetailStatsFragment

class PokemonDetailFragmentsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val pokemonDetails: Pokemon,
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonDetailAboutFragment(pokemonDetails)
            1 -> PokemonDetailStatsFragment(pokemonDetails)
//            2 -> PokemonDetailEvolutionFragment(pokemonDetails)
            else -> Fragment()
        }
    }
}