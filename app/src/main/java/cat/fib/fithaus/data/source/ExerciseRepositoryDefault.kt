package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation of [UserRepository].
 */
class ExerciseRepositoryDefault(
    private val exercisesRemoteDataSource: UserDataSource,
    private val exercisesLocalDataSource: UserDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {
    override fun observeExercises(): LiveData<Resource<List<Exercise>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercises(): Resource<List<Exercise>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshExercises() {
        TODO("Not yet implemented")
    }

    override fun observeExercise(exerciseId: String): LiveData<Resource<Exercise>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercise(exerciseId: String): Resource<Exercise> {

        try {
            updateTasksFromRemoteDataSource()
        } catch (ex: Exception) {
            return Resource.Error(ex)
        }
        return exercisesLocalDataSource.getExercise(exerciseId)
    }

    override suspend fun refreshExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveExercise(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllExercises() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }

    private suspend fun updateTasksFromRemoteDataSource() {
        val remoteExercises = exercisesRemoteDataSource.getExercises()

        if (remoteExercises is Resource.Success) {
            // Real apps might want to do a proper sync, deleting, modifying or adding each task.
            exercisesLocalDataSource.deleteAllExercises()
            remoteExercises.data.forEach { exercise ->
                exercisesLocalDataSource.saveExercise(exercise)
            }
        } else if (remoteExercises is Resource.Error) {
            throw remoteExercises.exception
        }
    }

}