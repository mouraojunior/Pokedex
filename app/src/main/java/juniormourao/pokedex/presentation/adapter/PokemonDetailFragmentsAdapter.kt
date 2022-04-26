package juniormourao.pokedex.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import juniormourao.pokedex.presentation.pokemon_details.about.PokemonDetailAboutFragment
import juniormourao.pokedex.presentation.pokemon_details.evolution.PokemonDetailEvolutionFragment
import juniormourao.pokedex.presentation.pokemon_details.stats.PokemonDetailStatsFragment

class PokemonDetailFragmentsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonDetailAboutFragment()
            1 -> PokemonDetailStatsFragment()
            2 -> PokemonDetailEvolutionFragment()
            else -> Fragment()
        }
    }
}