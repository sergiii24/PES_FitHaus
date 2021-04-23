package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.utils.Resource

/**
   Necessary functions to implement in the sources

 */
interface ExerciseDataSource {

    fun observeExercises(): LiveData<Resource<List<Exercise>>>

    suspend fun getExercises(): Resource<List<Exercise>>

    suspend fun refreshExercises()

    fun observeExercise(exerciseId: String): LiveData<Resource<Exercise>>

    suspend fun getExercise(exerciseId: String): Resource<Exercise>

    suspend fun refreshExercise(exerciseId: String)

    suspend fun saveExercise(exercise: Exercise)

    suspend fun updateExercise(exercise: Exercise)

    suspend fun deleteAllExercises()

    suspend fun deleteExercise(exerciseId: String)

}