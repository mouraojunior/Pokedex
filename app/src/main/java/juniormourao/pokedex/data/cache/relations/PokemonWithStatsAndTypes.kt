package juniormourao.pokedex.data.cache.relations

import androidx.room.Embedded
import androidx.room.Relation
import juniormourao.pokedex.data.cache.entity.PokemonEntity
import juniormourao.pokedex.data.cache.entity.PokemonStatEntity
import juniormourao.pokedex.data.cache.entity.PokemonTypeEntity
import juniormourao.pokedex.domain.model.Pokemon

data class PokemonWithStatsAndTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val pokemonStats: List<PokemonStatEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val pokemonTypes: List<PokemonTypeEntity>,
) {
    fun toPokemon() = Pokemon(
        baseExperience = pokemon.baseExperience,
        height = pokemon.height,
        id = pokemon.id,
        name = pokemon.name,
        order = pokemon.order,
        image = pokemon.image,
        stats = pokemonStats.map { it.toPokemonStatus() },
        types = pokemonTypes.map { it.toPokemonTypes() },
        weight = pokemon.weight
    )
}
