package juniormourao.pokedex.presentation.pokemon_details.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniormourao.pokedex.domain.model.PokemonSpecie
import juniormourao.pokedex.domain.repository.PokedexRepository
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailAboutViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository,
) : ViewModel() {
    private val _pokemonSpecie = MutableSharedFlow<Resource<PokemonSpecie>>()
    val pokemonSpecie = _pokemonSpecie.asSharedFlow()

    fun onEvent(event: PokemonDetailAboutEvent) {
        when (event) {
            is PokemonDetailAboutEvent.GetPokemonSpecie -> getPokemonSpecie(
                event.fetchFromRemote,
                event.pokemonId)
        }
    }

    private fun getPokemonSpecie(fetchFromRemote: Boolean, pokemonId: Int) {
        viewModelScope.launch(IO) {
            pokedexRepository.getPokemonSpecie(fetchFromRemote, pokemonId).collect { pokemonSpecieState ->
                when (pokemonSpecieState) {
                    is Resource.Error -> {
                        _pokemonSpecie.emit(Resource.Loading(false))
                        _pokemonSpecie.emit(
                            Resource.Error(
                                pokemonSpecieState.message ?: "An unknown error has occurred."
                            )
                        )
                    }
                    is Resource.Loading ->
                        _pokemonSpecie.emit(Resource.Loading(true))
                    is Resource.Success -> {
                        _pokemonSpecie.emit(Resource.Loading(false))
                        _pokemonSpecie.emit(Resource.Success(pokemonSpecieState.data))
                    }
                }
            }
        }
    }
}