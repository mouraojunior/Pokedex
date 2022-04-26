package juniormourao.pokedex.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import juniormourao.pokedex.R

object PokemonUtil {
    fun String.capitalizeFirstChar() = replaceFirstChar(Char::titlecase)

    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }

    fun getPokemonId(pokemonId: Int): String {
        return "#${pokemonId.toString().padStart(3, '0')}"
    }

    fun getPokemonResourcesByType(pokemonType: String): Triple<Int, Int, Int> = when (pokemonType) {
        "bug" -> Triple(R.color.background_type_bug,
            R.color.type_bug,
            R.drawable.ic_type_bug)
        "dark" ->
            Triple(R.color.background_type_dark,
                R.color.type_dark,
                R.drawable.ic_type_dark)
        "dragon" ->
            Triple(R.color.background_type_dragon,
                R.color.type_dragon,
                R.drawable.ic_type_dragon)
        "electric" ->
            Triple(R.color.background_type_electric,
                R.color.type_electric,
                R.drawable.ic_type_electric)
        "fairy" ->
            Triple(R.color.background_type_fairy,
                R.color.type_fairy,
                R.drawable.ic_type_fairy)
        "fighting" ->
            Triple(R.color.background_type_fighting,
                R.color.type_fighting,
                R.drawable.ic_type_fighting)
        "fire" ->
            Triple(R.color.background_type_fire,
                R.color.type_fire,
                R.drawable.ic_type_fire)
        "flying" ->
            Triple(R.color.background_type_flying,
                R.color.type_flying,
                R.drawable.ic_type_flying)
        "ghost" ->
            Triple(R.color.background_type_ghost,
                R.color.type_ghost,
                R.drawable.ic_type_ghost)
        "grass" ->
            Triple(R.color.background_type_grass,
                R.color.type_grass,
                R.drawable.ic_type_grass)
        "ground" ->
            Triple(R.color.background_type_ground,
                R.color.type_ground,
                R.drawable.ic_type_ground)
        "ice" ->
            Triple(R.color.background_type_ice,
                R.color.type_ice,
                R.drawable.ic_type_ice)
        "normal" ->
            Triple(R.color.background_type_normal,
                R.color.type_normal,
                R.drawable.ic_type_normal)
        "poison" ->
            Triple(R.color.background_type_poison,
                R.color.type_poison,
                R.drawable.ic_type_poison)
        "psychic" ->
            Triple(R.color.background_type_psychic,
                R.color.type_psychic,
                R.drawable.ic_type_psychic)
        "rock" ->
            Triple(R.color.background_type_rock,
                R.color.type_rock,
                R.drawable.ic_type_rock)
        "steel" ->
            Triple(R.color.background_type_steel,
                R.color.type_steel,
                R.drawable.ic_type_steel)
        "water" ->
            Triple(R.color.background_type_water,
                R.color.type_water,
                R.drawable.ic_type_water)
        else ->
            Triple(R.color.background_type_normal,
                R.color.type_normal,
                R.drawable.ic_type_normal)
    }

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
}