package juniormourao.pokedex.presentation.pokemon_list

sealed class PokemonListEvent {
    data class GetPokemons(val fetchFromRemote: Boolean) : PokemonListEvent()
}
