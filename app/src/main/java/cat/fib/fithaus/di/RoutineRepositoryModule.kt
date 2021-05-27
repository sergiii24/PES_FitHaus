package cat.fib.fithaus.di

import cat.fib.fithaus.data.api.RoutineService
import cat.fib.fithaus.data.source.*
import cat.fib.fithaus.data.source.local.FitHausDatabase
import cat.fib.fithaus.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * The binding for RoutineRepository has the default Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object RoutineRepositoryModule {
    @Singleton
    @Provides
    fun provideRoutineRepository(
        routineRepository: RoutineRepository,
        database: FitHausDatabase,
        appExecutors: AppExecutors
    ): RoutineRepository {
        return RoutineRepositoryDefault(
            database.customRoutineDao(), database.predefinedRoutineDao(), RoutineService, appExecutors
        )
    }
}