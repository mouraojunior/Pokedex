package juniormourao.pokedex.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Types(
    val slot: Int,
    val type: Type
): Parcelable