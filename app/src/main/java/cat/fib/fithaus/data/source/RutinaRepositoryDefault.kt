package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Rutina
import cat.fib.fithaus.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation of [RutinaRepository].
 */
class RutinaRepositoryDefault(
    private val exercisesRemoteDataSource: RutinaDataSource,
    private val exercisesLocalDataSource: RutinaDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RutinaRepository {
    override suspend fun getRutinaById(rutinaId: String): LiveData<Resource<Rutina>> {
        TODO("Not yet implemented")
    }


}

/*
    val remoteExercises = exercisesRemoteDataSource.getExercises()

    if (remoteExercises is Resource.Success<*>) {
        // Real apps might want to do a proper sync, deleting, modifying or adding each task.
        exercisesLocalDataSource.deleteAllExercises()
        remoteExercises.data.forEach { exercise ->
            exercisesLocalDataSource.saveExercise(exercise)
        }
    } else if (remoteExercises is Resource.Error) {
        throw remoteExercises.exception
    }
    */