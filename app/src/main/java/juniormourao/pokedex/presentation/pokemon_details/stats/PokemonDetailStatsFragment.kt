package juniormourao.pokedex.presentation.pokemon_details.stats

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonDetailStatsBinding
import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.util.PokemonUtil
import timber.log.Timber

class PokemonDetailStatsFragment(val pokemonDetails: Pokemon) : Fragment(R.layout.fragment_pokemon_detail_stats) {
    private lateinit var mainBinding: FragmentPokemonDetailStatsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentPokemonDetailStatsBinding.bind(view)
        setProgressBarsColor()
        setStatsValues()
        setProgressBarsProgression()
    }

    private fun setProgressBarsProgression() {
        mainBinding.apply {
            pbHP.progress = pokemonDetails.stats.first { it.stat.name == "hp" }.baseStat
            pbAttack.progress = pokemonDetails.stats.first { it.stat.name == "attack" }.baseStat
            pbDef.progress = pokemonDetails.stats.first { it.stat.name == "defense" }.baseStat
            pbSpAtk.progress = pokemonDetails.stats.first { it.stat.name == "special-attack" }.baseStat
            pbSpDef.progress = pokemonDetails.stats.first { it.stat.name == "special-defense" }.baseStat
            pbSpeed.progress = pokemonDetails.stats.first { it.stat.name == "speed" }.baseStat
        }
    }

    private fun setStatsValues() {
        mainBinding.apply {
            tvHPValue.text = pokemonDetails.stats.first { it.stat.name == "hp" }.baseStat.toString()
            tvAttackValue.text = pokemonDetails.stats.first { it.stat.name == "attack" }.baseStat.toString()
            tvDefValue.text = pokemonDetails.stats.first { it.stat.name == "defense" }.baseStat.toString()
            tvSpAttackValue.text = pokemonDetails.stats.first { it.stat.name == "special-attack" }.baseStat.toString()
            tvSpDefenseValue.text = pokemonDetails.stats.first { it.stat.name == "special-defense" }.baseStat.toString()
            tvSpeedValue.text = pokemonDetails.stats.first { it.stat.name == "speed" }.baseStat.toString()
        }
    }

    private fun setProgressBarsColor() {
        val pbColor = ContextCompat.getColor(requireContext(),
            PokemonUtil.getPokemonResourcesByType(
                pokemonDetails.types.first().type.name).first)

        mainBinding.apply {
            pbHP.progressTintList = ColorStateList.valueOf(pbColor)
            pbAttack.progressTintList = ColorStateList.valueOf(pbColor)
            pbDef.progressTintList = ColorStateList.valueOf(pbColor)
            pbSpAtk.progressTintList = ColorStateList.valueOf(pbColor)
            pbSpDef.progressTintList = ColorStateList.valueOf(pbColor)
            pbSpeed.progressTintList = ColorStateList.valueOf(pbColor)
        }
    }
}

