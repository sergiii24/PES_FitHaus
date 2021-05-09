package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.models.User

/**
 * Data Access Object for the exercise table.
 */
@Dao
interface ExerciseDao {

    /**
     * Select a exercise by id.
     *
     * @param exerciseId the exercise id.
     * @return the exercise with id.
     */
    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun getExerciseById(exerciseId: String): LiveData<Exercise>

    /**
     * Insert a exercise in the database. If the exercise already exists, replace it.
     *
     * @param exercise the exercise to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    /**
     * Observes list of tasks.
     *
     * @return all tasks.
     */
    @Query("SELECT * FROM exercises")
    fun observeAllExercises(): LiveData<List<Exercise>>

    /**
     * Observes a single exercise.
     *
     * @param id the task id.
     * @return the exercises with id.
     */
    @Query("SELECT * FROM exercises WHERE id = :id")
    fun observeById(id: String): LiveData<Exercise>

    /**
     * Select all exercises from the exercises table.
     *
     * @return all exercises.
     */
    @Query("SELECT * FROM exercises")
    suspend fun getExercises(): List<Exercise>

    /**
     * Update a exercise.
     *
     * @param exercise exercise to be updated
     * @return the number of exercise updated. This should always be 1.
     */
    @Update
    suspend fun updateExercise(exercise: Exercise): Int


    /**
     * Delete a exercise by id.
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM exercises WHERE id = :exerciseId")
    suspend fun deleteExerciseById(exerciseId: String): Int

    /**
     * Delete all exercises.
     */
    @Query("DELETE FROM exercises")
    suspend fun deleteExercises()

}