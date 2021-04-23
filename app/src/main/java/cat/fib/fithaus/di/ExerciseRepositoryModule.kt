package cat.fib.fithaus.di

import cat.fib.fithaus.data.source.ExerciseDataSource
import cat.fib.fithaus.data.source.ExerciseRepository
import cat.fib.fithaus.data.source.ExerciseRepositoryDefault
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * The binding for ExerciseRepository has the default Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object ExerciseRepositoryModule {
    @Singleton
    @Provides
    fun provideTasksRepository(
        @AppModule.RemoteTasksDataSource remoteExerciseDataSource: ExerciseDataSource,
        @AppModule.LocalTasksDataSource localExerciseDataSource: ExerciseDataSource,
        ioDispatcher: CoroutineDispatcher
    ): ExerciseRepository {
        return ExerciseRepositoryDefault(
            remoteExerciseDataSource, localExerciseDataSource, ioDispatcher
        )
    }
}