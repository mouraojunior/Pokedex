package juniormourao.pokedex.data.cache.entity


import androidx.room.Entity
import juniormourao.pokedex.domain.model.Stats
import juniormourao.pokedex.domain.model.StatsDetail

@Entity(tableName = "tbPokemonStat", primaryKeys = ["statDetailName", "pokemonId"])
data class PokemonStatEntity(
    val pokemonId: Int,
    val baseStat: Int,
    val effort: Int,
    val statDetailName: String,
    val statDetailUrl: String,
) {
    fun toPokemonStatus() = Stats(
        baseStat = baseStat,
        effort = effort,
        stat = StatsDetail(statDetailName, statDetailUrl)
    )
}