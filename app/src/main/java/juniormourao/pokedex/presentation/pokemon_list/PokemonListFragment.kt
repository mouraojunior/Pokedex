package juniormourao.pokedex.presentation.pokemon_list

import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonListBinding
import juniormourao.pokedex.presentation.adapter.PokemonListAdapter
import juniormourao.pokedex.util.PokemonUtil
import juniormourao.pokedex.util.PokemonUtil.createSnackBar
import juniormourao.pokedex.util.PokemonUtil.safeNavigate
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {
    private lateinit var pokemonListBinding: FragmentPokemonListBinding
    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListBinding = FragmentPokemonListBinding.bind(view)
        createAdapterAndItemClick()
        getPokemons(PokemonUtil.isNetworkAvailable(requireActivity()))
        pokemonListBinding.apply {
            rvPokemon.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = pokemonListAdapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonListViewModel.pokemons.collectLatest { pokemonState ->
                    when (pokemonState) {
                        is Resource.Error -> {
                            pokemonListBinding.apply {
                                pokemonListBinding.apply {
                                    rvPokemon.visibility = VISIBLE
                                    pbLoading.visibility = GONE
                                }
                                root.createSnackBar(
                                    message = pokemonState.message ?: ""
                                )
                            }
                        }
                        is Resource.Loading -> {
                            pokemonListBinding.apply {
                                rvPokemon.visibility = INVISIBLE
                                pbLoading.visibility = VISIBLE
                            }
                        }
                        is Resource.Success -> {
                            pokemonListBinding.apply {
                                rvPokemon.visibility = VISIBLE
                                pbLoading.visibility = GONE
                            }
                            pokemonListAdapter.submitList(pokemonState.data)
                        }
                    }
                }
            }
        }
    }

    private fun getPokemons(fetchFromRemote: Boolean) {
        pokemonListViewModel.onEvent(PokemonListEvent.GetPokemons(fetchFromRemote))
    }

    private fun createAdapterAndItemClick() {
        pokemonListAdapter = PokemonListAdapter(
            onItemClick = { pokemon ->
                findNavController().safeNavigate(
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                        pokemon)
                )
            }
        )
    }
}