package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.domain.model.StatsDetail

data class StatsDetailDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
) {
    fun toStatsDetail() = StatsDetail(
        name = name,
        url = url
    )
}