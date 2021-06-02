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
    fun getAchievementById(achievementId: Int): LiveData<Achievement>

    /**
     * Insert a achievement in the database. If the achievement already exists, replace it.
     *
     * @param achievement the achievementId to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAchievement(achievement: Achievement)


}