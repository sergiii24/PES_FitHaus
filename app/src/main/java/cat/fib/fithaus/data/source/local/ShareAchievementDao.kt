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
     * Select a shareAchievement by id.
     *
     * @param shareAchievementId the shareAchievement id.
     * @return the shareAchievement with id.
     */
    @Query("SELECT * FROM shareachievement WHERE user = :shareAchievementId")
    fun getShareAchievementById(shareAchievementId: Int): LiveData<List<ShareAchievement>>

    /**
     * Insert a shareAchievement in the database. If the shareAchievement already exists, replace it.
     *
     * @param shareAchievement the ShareAchievement to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShareAchievement(shareAchievement: ShareAchievement)


}