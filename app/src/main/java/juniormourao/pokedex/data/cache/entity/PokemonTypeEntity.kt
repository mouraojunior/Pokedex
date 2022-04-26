package juniormourao.pokedex.data.cache.entity


import androidx.room.Entity
import juniormourao.pokedex.domain.model.Type
import juniormourao.pokedex.domain.model.Types

@Entity(tableName = "tbPokemonType", primaryKeys = ["typeName", "pokemonId"])
data class PokemonTypeEntity(
    val typeName: String,
    val pokemonId: Int,
    val slot: Int,
) {
    fun toPokemonTypes() = Types(
        slot = slot,
        type = Type(name = typeName)
    )
}