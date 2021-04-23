package cat.fib.fithaus.data.source.remote

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.source.ExerciseDataSource
import cat.fib.fithaus.utils.Resource

object ExerciseRemoteDataSource : ExerciseDataSource {
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
        TODO("Not yet implemented")
    }

    override suspend fun refreshExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveExercise(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun updateExercise(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllExercises() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }
}
