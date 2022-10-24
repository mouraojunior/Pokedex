package juniormourao.pokedex.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import juniormourao.pokedex.data.cache.entity.PokemonEntity
import juniormourao.pokedex.data.cache.entity.PokemonSpecieEntity
import juniormourao.pokedex.data.cache.entity.PokemonStatEntity
import juniormourao.pokedex.data.cache.entity.PokemonTypeEntity

@Database(entities = [
    PokemonEntity::class,
    PokemonStatEntity::class,
    PokemonTypeEntity::class,
    PokemonSpecieEntity::class],
    version = 1)
abstract class PokedexDatabase : RoomDatabase() {
    abstract val pokedexDao: PokedexDao
}