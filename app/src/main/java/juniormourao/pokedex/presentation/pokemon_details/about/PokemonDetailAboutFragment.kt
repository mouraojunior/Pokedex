package juniormourao.pokedex.presentation.pokemon_details.about

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonDetailAboutBinding
import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.domain.model.PokemonSpecie
import juniormourao.pokedex.util.PokemonUtil
import juniormourao.pokedex.util.PokemonUtil.createSnackBar
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailAboutFragment(private val pokemonDetails: Pokemon) : Fragment(R.layout.fragment_pokemon_detail_about) {
    private lateinit var detailAboutBinding: FragmentPokemonDetailAboutBinding
    private val pokemonDetailAboutViewModel: PokemonDetailAboutViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailAboutBinding = FragmentPokemonDetailAboutBinding.bind(view)
        collectObservables()
        getPokemonSpecie(pokemonId = pokemonDetails.id,
            fetchFromRemote = PokemonUtil.isNetworkAvailable(requireActivity()))
    }

    private fun getPokemonSpecie(fetchFromRemote: Boolean, pokemonId: Int) {
        pokemonDetailAboutViewModel.onEvent(PokemonDetailAboutEvent.GetPokemonSpecie(fetchFromRemote, pokemonId))
    }

    private fun collectObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailAboutViewModel.pokemonSpecie.collectLatest { pokemonSpecie ->
                    when (pokemonSpecie) {
                        is Resource.Error ->
                            detailAboutBinding.apply {
                                root.createSnackBar(
                                    message = pokemonSpecie.message ?: ""
                                )
                            }
                        is Resource.Loading -> Unit
                        is Resource.Success -> bindPokemonSpecieToLayout(pokemonSpecie.data)
                    }
                }
            }
        }
    }

    private fun bindPokemonSpecieToLayout(pokemonSpecie: PokemonSpecie?) {
        pokemonSpecie?.let {
            setProgressBarsProgression(it)
            setStatsValues(it)
            setProgressBarsColor()
        }
    }

    private fun setProgressBarsProgression(pokemonSpecie: PokemonSpecie) {
        detailAboutBinding.apply {
            pbBaseHappiness.progress = pokemonSpecie.baseHappiness
            pbCaptureRate.progress = pokemonSpecie.captureRate
        }
    }

    private fun setStatsValues(pokemonSpecie: PokemonSpecie) {
        detailAboutBinding.apply {
            tvDescription.text = pokemonSpecie.description
            tvCaptureRateValue.text = pokemonSpecie.captureRate.toString()
            tvBaseHappinessValue.text = pokemonSpecie.baseHappiness.toString()
            tvWeightValue.text = pokemonDetails.weight.toString()
            tvHeightValue.text = pokemonDetails.height.toString()
            tvGenerationValue.text = pokemonSpecie.generation
            tvHabitatValue.text = pokemonSpecie.habitat
            tvIsBabyValue.text = pokemonSpecie.isBaby
            tvIsLegendaryValue.text = pokemonSpecie.isLegendary
            tvIsMythicalValue.text = pokemonSpecie.isMythical
        }
    }

    private fun setProgressBarsColor() {
        val pbColor = ContextCompat.getColor(requireContext(),
            PokemonUtil.getPokemonResourcesByType(
                pokemonDetails.types.first().type.name).first)

        detailAboutBinding.apply {
            pbBaseHappiness.progressTintList = ColorStateList.valueOf(pbColor)
            pbCaptureRate.progressTintList = ColorStateList.valueOf(pbColor)
        }
    }
}