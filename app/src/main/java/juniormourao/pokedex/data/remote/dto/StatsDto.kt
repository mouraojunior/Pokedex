package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.data.cache.entity.PokemonStatEntity
import juniormourao.pokedex.domain.model.Stats

data class StatsDto(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatsDetailDto,
) {
    fun toStats() = Stats(
        baseStat = baseStat,
        effort = effort,
        stat = stat.toStatsDetail()
    )

    fun toStatEntity(pokemonId: Int) = PokemonStatEntity(
        baseStat = baseStat,
        effort = effort,
        pokemonId = pokemonId,
        statDetailName = stat.toStatsDetail().name,
        statDetailUrl = stat.toStatsDetail().url
    )
}