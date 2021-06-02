package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cat.fib.fithaus.data.models.Achievement

/**
 * Data Access Object for the exercise table.
 */
@Dao
interface AchievementDao {

    /**
     * Select a achievement by id.
     *
     * @param achievementId the achievement id.
     * @return the achievement with id.
     */
    @Query("SELECT * FROM achievement WHERE id = :achievementId")
    fun getAchievementById(achievementId: String): LiveData<Achievement>

    /**
     * Insert a achievement in the database. If the achievement already exists, replace it.
     *
     * @param achievement the achievementId to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAchievement(achievement: Achievement)

    /**
     * Select all achievement from the achievement table.
     *
     * @return all achievement.
     */
    @Query("SELECT * FROM achievement")
    fun getAllAchievement(): LiveData<List<Achievement>>

    /**
     * Update a achievement.
     *
     * @param achievement achievement to be updated
     * @return the number of achievement updated. This should always be 1.
     */
    @Update
    suspend fun updateAchievement(achievement: Achievement): Int

    /**
     * Delete a achievement by id.
     *
     * @param achievementId shareAchievement to be deleted
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM achievement WHERE id = :achievementId")
    suspend fun deleteAchievementById(achievementId: String): Int

    /**
     * Delete all achievements.
     */
    @Query("DELETE FROM achievement")
    suspend fun deleteAchievements()

}