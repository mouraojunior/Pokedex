package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TypeDto(
    @SerializedName("name")
    val name: String
)