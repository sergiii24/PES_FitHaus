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
    suspend fun getUserById(userId: String): LiveData<User>?

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Update a user.
     *
     * @param user user to be updated
     * @return the number of user updated. This should always be 1.
     */
    @Update
    suspend fun updateUser(user: User): Int


    /**
     * Delete a user by id.
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteUserById(userId: String): Int

    /**
     * Delete all users.
     */
    @Query("DELETE FROM user")
    suspend fun deleteUsers()

}