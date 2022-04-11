package juniormourao.pokedex.domain.repository

import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    suspend fun getPokemons(): Flow<Resource<List<PokemonDetailResponseDto>>>
}