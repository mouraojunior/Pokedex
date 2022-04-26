package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.data.cache.entity.PokemonTypeEntity
import juniormourao.pokedex.domain.model.Types

data class TypesDto(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDto,
) {
    fun toTypes() = Types(
        slot = slot,
        type = type.toType()
    )

    fun toTypeEntity(pokemonId: Int) = PokemonTypeEntity(
        pokemonId = pokemonId,
        slot = slot,
        typeName = type.name
    )
}