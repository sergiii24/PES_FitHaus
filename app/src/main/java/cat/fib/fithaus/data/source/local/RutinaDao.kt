package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cat.fib.fithaus.data.models.Rutina
import cat.fib.fithaus.utils.Resource

/**
 * Data Access Object for the rutina table.
 */
@Dao
interface RutinaDao {

    /**
     * Select a rutina by id.
     *
     * @param rutinaId the rutina id.
     * @return the rutina with id.
     */
    @Query("SELECT * FROM Rutina WHERE id = :rutinaId")
    fun getRutinaById(rutinaId: String): LiveData<Resource<Rutina>>?

}