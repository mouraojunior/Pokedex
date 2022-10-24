package juniormourao.pokedex.domain.model

data class PokemonSpecie(
    val pokemonId: Int,
    val baseHappiness: Int,
    val captureRate: Int,
    val description: String,
    val generation: String,
    val habitat: String,
    val isBaby: String,
    val isLegendary: String,
    val isMythical: String,
)