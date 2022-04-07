package juniormourao.pokedex.presentation.pokemon_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {
    private lateinit var pokemonListBinding: FragmentPokemonListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListBinding = FragmentPokemonListBinding.bind(view)
    }
}