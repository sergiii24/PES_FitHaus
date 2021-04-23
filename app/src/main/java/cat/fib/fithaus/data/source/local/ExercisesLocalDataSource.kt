package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.source.ExerciseDataSource
import cat.fib.fithaus.utils.Resource
import cat.fib.fithaus.utils.Resource.Companion.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class ExercisesLocalDataSource internal constructor(
    private val exerciseDao: ExerciseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExerciseDataSource {


    override fun observeExercises(): LiveData<Resource<List<Exercise>>> {
        return exerciseDao.observeAllExercises().map {
            success(it)
        }
    }

    override suspend fun getExercises(): Resource<List<Exercise>> = withContext(ioDispatcher) {
        return@withContext try {
            success(exerciseDao.getExercises())
        } catch (e: Exception) {
            error(e)
        }
    }

    override suspend fun refreshExercises() {
        TODO("Not yet implemented")
    }

    override fun observeExercise(exerciseId: String): LiveData<Resource<Exercise>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercise(exerciseId: String): Resource<Exercise> =
        withContext(ioDispatcher) {
            try {
                val exercise = exerciseDao.getExerciseById(exerciseId)
                if (exercise != null) {
                    return@withContext success(exercise)
                } else {
                    return@withContext error(Exception("Exercise not found!"))
                }
            } catch (e: Exception) {
                return@withContext error(e)
            }
        }

    override suspend fun refreshExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveExercise(exercise: Exercise) = withContext(ioDispatcher) {
        exerciseDao.insertExercise(exercise)
    }


    override suspend fun updateExercise(exercise: Exercise): Unit = withContext(ioDispatcher) {
        exerciseDao.updateExercise(exercise)
    }

    override suspend fun deleteAllExercises() = withContext(ioDispatcher) {
        exerciseDao.deleteExercises()
    }

    override suspend fun deleteExercise(exerciseId: String) = withContext<Unit>(ioDispatcher) {
        exerciseDao.deleteExerciseById(exerciseId)
    }
}