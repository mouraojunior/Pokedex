package juniormourao.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto
)