package juniormourao.pokedex.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val baseStat: Int,
    val effort: Int,
    val stat: StatsDetail
): Parcelable