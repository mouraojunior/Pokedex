package juniormourao.pokedex.presentation.pokemon_details.about

sealed class PokemonDetailAboutEvent {
    data class GetPokemonSpecie(val fetchFromRemote: Boolean, val pokemonId: Int) : PokemonDetailAboutEvent()
}
