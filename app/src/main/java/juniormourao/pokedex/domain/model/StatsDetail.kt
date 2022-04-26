package juniormourao.pokedex.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatsDetail(
    val name: String,
    val url: String,
) : Parcelable