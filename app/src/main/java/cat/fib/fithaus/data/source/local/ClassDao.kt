package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.fib.fithaus.data.models.Class

/**
 * Data Access Object for the class table.
 */
@Dao
interface ClassDao {

    /**
     * Select a class by id.
     *
     * @param classId the class id.
     * @return the class with id.
     */
    @Query("SELECT * FROM classes WHERE id = :classId")
    fun getClassById(classId: String): LiveData<Class>

    /**
     * Insert a class in the database. If the class already exists, replace it.
     *
     * @param classe the class to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClass(classe: Class)

}