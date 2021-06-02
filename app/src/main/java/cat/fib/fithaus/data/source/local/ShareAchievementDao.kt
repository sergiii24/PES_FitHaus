package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cat.fib.fithaus.data.models.ShareAchievement

/**
 * Data Access Object for the exercise table.
 */
@Dao
interface ShareAchievementDao {

    /**
     * Select a achievement by id.
     *
     * @param shareAchievementId the shareAchievement id.
     * @return the shareAchievement with id.
     */
    @Query("SELECT * FROM shareachievement WHERE id = :shareAchievementId")
    fun getShareAchievementById(shareAchievementId: String): LiveData<ShareAchievement>

    /**
     * Insert a shareAchievement in the database. If the shareAchievement already exists, replace it.
     *
     * @param shareAchievement the ShareAchievement to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShareAchievement(shareAchievement: ShareAchievement)

    /**
     * Select all shareAchievement from the shareachievement table.
     *
     * @return all achievement.
     */
    @Query("SELECT * FROM shareachievement")
    fun getAllSahreAchievement(): LiveData<List<ShareAchievement>>

    /**
     * Update a shareAchievement.
     *
     * @param shareAchievement shareAchievement to be updated
     * @return the number of shareAchievement updated. This should always be 1.
     */
    @Update
    suspend fun updateShareAchievement(shareAchievement: ShareAchievement): Int

    /**
     * Delete a shareAchievement by id.
     *
     * @param shareAchievementId shareAchievement to be deleted
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM shareachievement WHERE id = :shareAchievementId")
    suspend fun deleteAchievementById(shareAchievementId: String): Int

    /**
     * Delete all shareAchievements.
     */
    @Query("DELETE FROM shareachievement")
    suspend fun deleteShareAchievements()

}