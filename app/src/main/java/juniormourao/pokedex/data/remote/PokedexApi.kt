package juniormourao.pokedex.data.remote

import juniormourao.pokedex.data.remote.dto.PokemonDetailResponseDto
import juniormourao.pokedex.data.remote.dto.PokemonResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokemons(): PokemonResponseDto

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemonName: String
    ): PokemonDetailResponseDto

    companion object {
        const val API_BASE_URL = "https://pokeapi.co/api/v2/"
    }
}