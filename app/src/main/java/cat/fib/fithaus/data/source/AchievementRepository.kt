package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Achievement
import cat.fib.fithaus.utils.Resource

interface AchievementRepository {

    fun getAchievementById(achievementId: Int): LiveData<Resource<Achievement>>

}