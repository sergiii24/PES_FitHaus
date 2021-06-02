package cat.fib.fithaus.di

import cat.fib.fithaus.data.api.ExerciseService
import cat.fib.fithaus.data.api.ClassService
import cat.fib.fithaus.data.api.UserService
import cat.fib.fithaus.data.source.*
import cat.fib.fithaus.data.source.local.FitHausDatabase
import cat.fib.fithaus.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The binding for ExerciseRepository has the default Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object ExerciseRepositoryModule {
    @Singleton
    @Provides
    fun provideExerciseRepository(
        exerciseService: ExerciseService,
        database: FitHausDatabase,
        appExecutors: AppExecutors
    ): ExerciseRepository {
        return ExerciseRepositoryDefault(
            database.exerciseDao(), exerciseService, appExecutors
        )
    }
}

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

/**
 * The binding for ClassRepository has the default Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object ClassRepositoryModule {
    @Singleton
    @Provides
    fun provideClassRepository(
            classService: ClassService,
            database: FitHausDatabase,
            appExecutors: AppExecutors
    ): ClassRepository {
        return ClassRepositoryDefault(
                database.classDao(), classService, appExecutors
        )
    }
}