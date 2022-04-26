package juniormourao.pokedex.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbPokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val baseExperience: Int,
    val height: Int,
    val name: String,
    val order: Int,
    val image: String,
    val weight: Int,
)