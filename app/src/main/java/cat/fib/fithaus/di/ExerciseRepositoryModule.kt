package cat.fib.fithaus.di

import cat.fib.fithaus.data.api.UserService
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
 * The binding for ExerciseRepository has the default Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userService: UserService,
        database: FitHausDatabase,
        appExecutors: AppExecutors
    ): UserRepository {
        return UserRepositoryDefault(
            database.userDao(), userService, appExecutors
        )
    }
}