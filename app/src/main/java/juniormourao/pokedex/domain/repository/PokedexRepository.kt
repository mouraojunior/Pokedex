package juniormourao.pokedex.domain.repository

import juniormourao.pokedex.domain.model.Pokemon
import juniormourao.pokedex.domain.model.PokemonSpecie
import juniormourao.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    suspend fun getPokemons(fetchFromRemote: Boolean): Flow<Resource<List<Pokemon>>>
    suspend fun getPokemonSpecie(fetchFromRemote: Boolean, pokemonId: Int): Flow<Resource<PokemonSpecie>>
}