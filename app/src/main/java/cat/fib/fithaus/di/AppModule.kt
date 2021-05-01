package cat.fib.fithaus.di

import android.content.Context
import androidx.room.Room
import cat.fib.fithaus.data.api.ApiServices
import cat.fib.fithaus.data.api.Configuration
import cat.fib.fithaus.data.api.ExerciseService
import cat.fib.fithaus.data.source.ExerciseDataSource
import cat.fib.fithaus.data.source.local.ExercisesLocalDataSource
import cat.fib.fithaus.data.source.local.FitHausDatabase
import cat.fib.fithaus.data.source.remote.ExerciseRemoteDataSource
import cat.fib.fithaus.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteTasksDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalTasksDataSource

    @Singleton
    @RemoteTasksDataSource
    @Provides
    fun provideTasksRemoteDataSource(
        exerciseService: ExerciseService
    ): ExerciseDataSource {
        return ExerciseRemoteDataSource(
            exerciseService
        )
    }

    @Singleton
    @LocalTasksDataSource
    @Provides
    fun provideExerciseLocalDataSource(
        database: FitHausDatabase,
        ioDispatcher: CoroutineDispatcher
    ): ExerciseDataSource {
        return ExercisesLocalDataSource(
            database.exerciseDao(), ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): FitHausDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FitHausDatabase::class.java,
            "FitHaus.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}