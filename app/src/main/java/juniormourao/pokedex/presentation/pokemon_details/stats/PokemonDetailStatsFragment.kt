package juniormourao.pokedex.presentation.pokemon_details.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonDetailStatsBinding

class PokemonDetailStatsFragment : Fragment(R.layout.fragment_pokemon_detail_stats) {
    private lateinit var mainBinding: FragmentPokemonDetailStatsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentPokemonDetailStatsBinding.bind(view)
    }
}