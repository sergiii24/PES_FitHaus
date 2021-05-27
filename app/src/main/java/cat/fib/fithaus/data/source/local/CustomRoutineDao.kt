package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.fib.fithaus.data.models.CustomRoutine

/**
 * Data Access Object for the CustomRoutine table.
 */
@Dao
interface CustomRoutineDao {

    /**
     * Select a CustomRoutine by id.
     *
     * @param rutinaId the rutina id.
     * @return the rutina with id.
     */
    @Query("SELECT * FROM CustomRoutine WHERE id = :rutinaId")
    fun getCustomRoutineById(rutinaId: String): LiveData<CustomRoutine>

    /**
     * Insert a routine in the database. If the routine already exists, replace it.
     *
     * @param customRoutine the routine to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomRoutine(routine: CustomRoutine)
}