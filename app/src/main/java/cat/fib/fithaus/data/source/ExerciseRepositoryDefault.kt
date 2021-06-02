package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.ExerciseService
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.local.ExerciseDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource

/**
 * Default implementation of [ExerciseRepository].
 */
class ExerciseRepositoryDefault(
    private val exerciseDao: ExerciseDao,
    private val exerciseService: ExerciseService,
    private val appExecutors: AppExecutors,
) : ExerciseRepository {

    override fun getExercise(exerciseName: String): LiveData<Resource<Exercise>> {
        return object : NetworkBoundResource<Exercise, Exercise>(appExecutors) {
            override fun saveCallResult(item: Exercise) {
                exerciseDao.insertExercise(item)
            }

            override fun shouldFetch(data: Exercise?) = data == null

            override fun loadFromDb() = exerciseDao.getExerciseByName(exerciseName)

            override fun createCall() = exerciseService.getExercise(exerciseName)
        }.asLiveData()
    }

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
        TODO("Not yet implemented")
    }

}