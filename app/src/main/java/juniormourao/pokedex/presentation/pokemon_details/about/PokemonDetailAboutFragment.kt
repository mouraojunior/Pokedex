package juniormourao.pokedex.presentation.pokemon_details.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonDetailAboutBinding
import juniormourao.pokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailAboutFragment : Fragment(R.layout.fragment_pokemon_detail_about) {
    private lateinit var detailAboutBinding: FragmentPokemonDetailAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailAboutBinding = FragmentPokemonDetailAboutBinding.bind(view)
    }
}