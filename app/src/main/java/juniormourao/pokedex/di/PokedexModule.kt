package juniormourao.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import juniormourao.pokedex.data.remote.PokedexApi
import juniormourao.pokedex.data.remote.PokedexApi.Companion.API_BASE_URL
import juniormourao.pokedex.data.repository.PokedexRepositoryImpl
import juniormourao.pokedex.domain.repository.PokedexRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {
    @Provides
    @Singleton
    fun providePokedexApi(): PokedexApi =
        Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(PokedexApi::class.java)

    @Provides
    @Singleton
    fun providePokedexRepository(
        pokedexApi: PokedexApi
    ): PokedexRepository = PokedexRepositoryImpl(
        pokedexApi = pokedexApi
    )
}

