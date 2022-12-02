package com.uli.androidapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.uli.androidapp.BuildConfig.BASE_URL
import com.uli.androidapp.data.local.db.CharacterDao
import com.uli.androidapp.data.local.db.CharacterDatabase
import com.uli.androidapp.data.local.repository.LocalRepositoryImpl
import com.uli.androidapp.data.network.api.ApiService
import com.uli.androidapp.data.network.repository.RickAndMortyRepositoryImpl
import com.uli.androidapp.domain.local.usecase.AddCharacterUseCase
import com.uli.androidapp.domain.local.usecase.GetAllCharactersResultUseCase
import com.uli.androidapp.domain.network.usecases.GetCharacterByIdUseCase
import com.uli.androidapp.domain.network.usecases.GetCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideGetCharacterUseCase(repository: RickAndMortyRepositoryImpl): GetCharacterUseCase {
        return GetCharacterUseCase(repository)
    }

    @Provides
    fun provideGetCharacterByIdUseCase(repository: RickAndMortyRepositoryImpl): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCase(repository)
    }
    @Provides
    fun provideGetCharacterByLocalDb(repository: LocalRepositoryImpl): GetAllCharactersResultUseCase {
        return GetAllCharactersResultUseCase(repository)
    }

    @Provides
    fun provideAddCharacterLocalDb(repository: LocalRepositoryImpl): AddCharacterUseCase {
        return AddCharacterUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
       return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "DB_NAME"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: CharacterDatabase): CharacterDao {
        return db.getDao()
    }
}