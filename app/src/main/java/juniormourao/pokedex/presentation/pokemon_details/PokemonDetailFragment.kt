package juniormourao.pokedex.presentation.pokemon_details

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import juniormourao.pokedex.R
import juniormourao.pokedex.databinding.FragmentPokemonDetailBinding
import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.presentation.adapter.PokemonDetailFragmentsAdapter
import juniormourao.pokedex.util.PokemonUtil
import juniormourao.pokedex.util.PokemonUtil.capitalizeFirstChar
import juniormourao.pokedex.util.PokemonUtil.getPokemonId

@AndroidEntryPoint
class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {
    private lateinit var pokemonDetailBinding: FragmentPokemonDetailBinding
    private lateinit var pokemonDetailsTabAdapter: PokemonDetailFragmentsAdapter
    private lateinit var pokemonDetails: Pokemon
    private val args: PokemonDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonDetailBinding = FragmentPokemonDetailBinding.bind(view)
        getArgs()
        setPokemonDetailsTabs()
        bindPokemonDetails(pokemonDetails)


        pokemonDetailBinding.btnBackButton.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }


    private fun setPokemonDetailsTabs() {
        pokemonDetailsTabAdapter = PokemonDetailFragmentsAdapter(
            parentFragmentManager,
            lifecycle,
            pokemonDetails)
        pokemonDetailBinding.apply {
            vpgrPokemonDetails.adapter = pokemonDetailsTabAdapter
            TabLayoutMediator(tblPokemonDetails, vpgrPokemonDetails) { tab, position ->
                when (position) {
                    0 -> tab.text = resources.getString(R.string.tab_about)
                    1 -> tab.text = resources.getString(R.string.tab_stats)
                    2 -> tab.text = resources.getString(R.string.tab_evolution)
                }
            }.attach()
        }
    }

    private fun getArgs() {
        pokemonDetails = args.pokemon
    }

    private fun bindPokemonDetails(pokemonDetails: Pokemon) {
        pokemonDetailBinding.apply {
            tvPokemonName.text = pokemonDetails.name.capitalizeFirstChar()
            tvPokemonId.text = getPokemonId(pokemonDetails.id)
            imgPokemon.load(pokemonDetails.image)

            pokemonDetails.types.forEach { pokemonType ->
                val pokemonTypeSlot = pokemonType.slot
                val pokemonResources =
                    PokemonUtil.getPokemonResourcesByType(pokemonType.type.name)
                val backgroundTypeColor = pokemonResources.first
                val typeColor = pokemonResources.second
                val pokemonTypeIcon = pokemonResources.third

                when (pokemonTypeSlot) {
                    1 -> {
                        tblPokemonDetails.setTabTextColors(ContextCompat.getColor(requireContext(),
                            R.color.unselected_tab), ContextCompat.getColor(requireContext(),
                            typeColor))
                        tblPokemonDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(
                            requireContext(),
                            typeColor))

                        imgPokemonType1.setImageResource(pokemonTypeIcon)
                        tvPokemonType1.text = pokemonType.type.name.capitalizeFirstChar()
                        cvPokemonType1.setCardBackgroundColor(
                            ContextCompat.getColor(
                                pokemonDetailBinding.root.context,
                                typeColor
                            )
                        )
                        clBase.setBackgroundColor(
                            ContextCompat.getColor(
                                pokemonDetailBinding.root.context,
                                backgroundTypeColor
                            )
                        )
                    }
                    2 -> {
                        imgPokemonType2.setImageResource(pokemonTypeIcon)
                        tvPokemonType2.text = pokemonType.type.name.capitalizeFirstChar()
                        cvPokemonType2.setCardBackgroundColor(
                            ContextCompat.getColor(
                                pokemonDetailBinding.root.context,
                                typeColor
                            )
                        )
                    }
                }
            }
            if (pokemonDetails.types.size == 1) cvPokemonType2.visibility = View.GONE
            else cvPokemonType2.visibility = View.VISIBLE
        }
    }
}