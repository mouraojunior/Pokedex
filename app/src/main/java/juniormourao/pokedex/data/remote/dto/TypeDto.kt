package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName
import juniormourao.pokedex.domain.model.Type

data class TypeDto(
    @SerializedName("name")
    val name: String,
) {
    fun toType() = Type(
        name = name
    )
}