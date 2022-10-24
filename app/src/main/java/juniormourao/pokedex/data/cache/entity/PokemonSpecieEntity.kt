package juniormourao.pokedex.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import juniormourao.pokedex.domain.model.PokemonSpecie

@Entity(tableName = "tbPokemonSpecie")
data class PokemonSpecieEntity(
    @PrimaryKey val pokemonId: Int,
    val baseHappiness: Int,
    val captureRate: Int,
    val description: String,
    val generation: String,
    val habitat: String,
    val isBaby: String,
    val isLegendary: String,
    val isMythical: String,
) {
    fun toPokemonSpecie(pokemonId: Int) =
        PokemonSpecie(
            pokemonId = pokemonId,
            baseHappiness = baseHappiness,
            captureRate = captureRate,
            description = description,
            generation = generation,
            habitat = habitat,
            isBaby = isBaby,
            isLegendary = isLegendary,
            isMythical = isMythical,
        )
}