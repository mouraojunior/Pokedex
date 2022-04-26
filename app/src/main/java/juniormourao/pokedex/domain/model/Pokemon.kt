package juniormourao.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val image: String,
    val stats: List<Stats>,
    val types: List<Types>,
    val weight: Int,
): Parcelable