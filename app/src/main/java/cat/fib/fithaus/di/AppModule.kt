package cat.fib.fithaus.di

import cat.fib.fithaus.data.api.ApiServices
import cat.fib.fithaus.data.api.Configuration
import cat.fib.fithaus.data.api.ExerciseService
import cat.fib.fithaus.utils.LiveDataCallAdapterFactory
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

class AppModule {

    @Singleton
    @Provides
    fun provideExerciseService(): ExerciseService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(Configuration.urlServer)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ExerciseService::class.java)
    }

}