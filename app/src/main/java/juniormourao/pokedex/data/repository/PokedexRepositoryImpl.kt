package juniormourao.pokedex.data.repository

import juniormourao.pokedex.data.remote.PokedexApi
import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.domain.repository.PokedexRepository
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi
) : PokedexRepository {
    // TODO(Return type should use the type of domain module)
    override suspend fun getPokemons(): Flow<Resource<List<PokemonDetailResponseDto>>> =
        flow {
            val pokemonDetails: List<PokemonDetailResponseDto>
            emit(Resource.Loading(true))
            try {
                coroutineScope {
                    pokemonDetails = pokedexApi.getPokemons().results.map { pokemon ->
                        async { pokedexApi.getPokemonDetail(pokemon.name) }
                    }.awaitAll()
                }
                emit(Resource.Success(pokemonDetails))
            } catch (e: Exception) {
                emit(Resource.Error("An unexpected error has occurred.", emptyList()))
            }
        }
}