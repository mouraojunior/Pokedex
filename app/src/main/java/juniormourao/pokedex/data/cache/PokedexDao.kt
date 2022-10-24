package juniormourao.pokedex.data.cache

import androidx.room.*
import juniormourao.pokedex.data.cache.entity.PokemonEntity
import juniormourao.pokedex.data.cache.entity.PokemonSpecieEntity
import juniormourao.pokedex.data.cache.entity.PokemonStatEntity
import juniormourao.pokedex.data.cache.entity.PokemonTypeEntity
import juniormourao.pokedex.data.cache.relations.PokemonWithStatsAndTypes

@Dao
interface PokedexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonSpecie(specie: PokemonSpecieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonStats(pokemonStats: List<PokemonStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonTypes(pokemonTypes: List<PokemonTypeEntity>)

    @Query("SELECT * FROM tbPokemonSpecie WHERE pokemonId = :pokemonId")
    suspend fun getPokemonSpecie(pokemonId: Int): PokemonSpecieEntity

    @Transaction
    @Query("SELECT distinct poke.* FROM tbPokemon poke" +
            " LEFT JOIN tbPokemonStat stat ON (stat.pokemonId = poke.id)" +
            " LEFT JOIN tbPokemonType type ON (type.pokemonId = poke.id)")
    suspend fun getPokemonWithStatsAndTypes(): List<PokemonWithStatsAndTypes>
}