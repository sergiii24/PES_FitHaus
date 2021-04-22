package cat.fib.fithaus

import cat.fib.fithaus.exercise.ExerciseLocalDataSource
import cat.fib.fithaus.exercise.ExerciseRemoteDataSource
import cat.fib.fithaus.exercise.ExerciseRepository
import cat.fib.fithaus.factories.ExerciseViewModelFactory

class AppContainer {

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before

    private val remoteDataSource = ExerciseRemoteDataSource()
    private val localDataSource = ExerciseLocalDataSource()

    // userRepository is not private; it'll be exposed
    val exerciseRepository = ExerciseRepository(localDataSource, remoteDataSource)
    val loginViewModelFactory = ExerciseViewModelFactory(exerciseRepository)


}