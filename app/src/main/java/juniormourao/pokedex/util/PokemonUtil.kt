package juniormourao.pokedex.util

object PokemonUtil {
    fun String.capitalizeFirstChar() = replaceFirstChar(Char::titlecase)
}