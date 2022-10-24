package juniormourao.pokedex.data.remote

import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.data.remote.dto.PokemonResponseDto
import juniormourao.pokedex.data.remote.dto.PokemonSpecieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokemons(): PokemonResponseDto

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemonName: String
    ): PokemonDetailResponseDto

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonSpecie(
        @Path("pokemonId") pokemonId: Int
    ): PokemonSpecieResponseDto

    companion object {
        const val API_BASE_URL = "https://pokeapi.co/api/v2/"
    }
}