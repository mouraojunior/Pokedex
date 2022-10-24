package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.data.cache.entity.PokemonSpecieEntity
import juniormourao.pokedex.util.PokemonUtil.capitalizeFirstChar

data class PokemonSpecieResponseDto(
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryDto>,
    @SerializedName("generation")
    val generation: GenerationDto,
    @SerializedName("habitat")
    val habitat: HabitatDto,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
) {
    fun toPokemonSpecieEntity(pokemonId: Int) =
        PokemonSpecieEntity(
            pokemonId = pokemonId,
            baseHappiness = baseHappiness,
            captureRate = captureRate,
            description = flavorTextEntries.first { it.language.name == "en" }.flavorText.replace("\n", " "),
            generation = generation.name.replace("-", " ").uppercase(),
            habitat = habitat.name.capitalizeFirstChar(),
            isBaby = if (isBaby) "Yes" else "No",
            isLegendary = if (isLegendary) "Yes" else "No",
            isMythical = if (isMythical) "Yes" else "No",
        )
}