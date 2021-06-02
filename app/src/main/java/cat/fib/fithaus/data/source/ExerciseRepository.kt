package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.utils.Resource

interface ExerciseRepository {

    fun getExercise(exerciseName: String): LiveData<Resource<Exercise>>

    fun observeExercises(): LiveData<Resource<List<Exercise>>>

    suspend fun getExercises(): Resource<List<Exercise>>

    suspend fun refreshExercises()

    fun observeExercise(exerciseId: String): LiveData<Resource<Exercise>>

    suspend fun refreshExercise(exerciseId: String)

    suspend fun saveExercise(exercise: Exercise)

    suspend fun deleteAllExercises()

    suspend fun deleteExercise(exerciseId: String)
}