package juniormourao.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.domain.repository.PokedexRepository
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {
    private val _pokemons = MutableSharedFlow<Resource<List<PokemonDetailResponseDto>>>()
    val pokemons = _pokemons.asSharedFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            pokedexRepository.getPokemons().collect { pokemonState ->
                when (pokemonState) {
                    is Resource.Error -> {
                        _pokemons.emit(Resource.Loading(false))
                        _pokemons.emit(
                            Resource.Error(
                                pokemonState.message ?: "An unknown error has occurred."
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _pokemons.emit(Resource.Loading(true))
                    }
                    is Resource.Success -> {
                        _pokemons.emit(Resource.Loading(false))
                        _pokemons.emit(Resource.Success(pokemonState.data))
                    }
                }

            }
        }
    }

}