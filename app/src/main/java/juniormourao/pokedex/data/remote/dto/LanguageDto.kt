package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LanguageDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)