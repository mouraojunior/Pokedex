package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.data.cache.entity.PokemonEntity
import juniormourao.pokedex.domain.model.Pokemon

data class PokemonDetailResponseDto(
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("sprites")
    val sprites: SpritesDto,
    @SerializedName("stats")
    val stats: List<StatsDto>,
    @SerializedName("types")
    val types: List<TypesDto>,
    @SerializedName("weight")
    val weight: Int,
) {
    fun toPokemon() = Pokemon(
        baseExperience = baseExperience,
        height = height,
        id = id,
        name = name,
        order = order,
        image = sprites.other.officialArtwork.frontDefault,
        stats = stats.map { it.toStats() },
        types = types.map { it.toTypes() },
        weight = weight
    )

    fun toPokemonEntity() = PokemonEntity(
        baseExperience = baseExperience,
        height = height,
        id = id,
        name = name,
        order = order,
        image = sprites.other.officialArtwork.frontDefault,
        weight = weight
    )
}