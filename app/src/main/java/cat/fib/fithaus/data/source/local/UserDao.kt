package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cat.fib.fithaus.data.models.User

/**
 * Data Access Object for the user table.
 */
@Dao
interface UserDao {

    /**
     * Select a user by id.
     *
     * @param userId the user id.
     * @return the user with id.
     */
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: String): LiveData<User>


    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): LiveData<User>

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

}