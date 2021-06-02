package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.fib.fithaus.data.models.Program

@Dao
interface ProgramDao {

    /**
     * Select a program by id.
     *
     * @param programId the program id.
     * @return the program with id = programId
     */
    @Query("SELECT * FROM program WHERE id = :programId")
    fun getProgramById(programId: Int): LiveData<Program>

    /**
     * Insert a program in the database. If the program already exists, replace it.
     *
     * @param program the program to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProgram(program: Program)

}