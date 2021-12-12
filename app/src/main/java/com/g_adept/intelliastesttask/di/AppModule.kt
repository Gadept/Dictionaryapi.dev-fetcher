package com.g_adept.intelliastesttask.di

import android.content.Context
import com.g_adept.intelliastesttask.data.local.AppRoomDatabase
import com.g_adept.intelliastesttask.data.local.WordDao
import com.g_adept.intelliastesttask.data.remote.WordRemoteDataSource
import com.g_adept.intelliastesttask.data.remote.WordService
import com.g_adept.intelliastesttask.data.repository.WordRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WordService =
        retrofit.create(WordService::class.java)

    @Singleton
    @Provides
    fun provideWordRemoteDataSource(wordService: WordService) =
        WordRemoteDataSource(wordService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideWordDao(db: AppRoomDatabase) = db.wordDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: WordRemoteDataSource,
        localDataSource: WordDao
    ) =
        WordRepository(remoteDataSource, localDataSource)
}