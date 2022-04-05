package juniormourao.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<BasicPokemonDto>
)