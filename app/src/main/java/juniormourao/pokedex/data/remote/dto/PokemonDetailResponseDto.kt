package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

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
    val weight: Int
)