package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.fib.fithaus.data.models.PredefinedRoutine

/**
 * Data Access Object for the PredefinedRoutine table.
 */
@Dao
interface PredefinedRoutineDao {

    /**
     * Select a rutina by id.
     *
     * @param rutinaId the rutina id.
     * @return the rutina with id.
     */
    @Query("SELECT * FROM PredefinedRoutine WHERE id = :rutinaId")
    fun getPredefinedRoutineById(rutinaId: String): LiveData<PredefinedRoutine>
    /**
     * Insert a routine in the database. If the routine already exists, replace it.
     *
     * @param predefinedRoutine the routine to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPredefinedRoutine(routine: PredefinedRoutine)
}