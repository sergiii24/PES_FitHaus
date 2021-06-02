package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.AchievementService
import cat.fib.fithaus.data.models.Achievement
import cat.fib.fithaus.data.source.local.AchievementDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource

class AchievementRepositoryDefault(
    private val achievementDao: AchievementDao,
    private val achievementService: AchievementService,
    private val appExecutors: AppExecutors
) : AchievementRepository{

    override fun getAchievementById(achievementId: Int): LiveData<Resource<Achievement>> {
        return object : NetworkBoundResource<Achievement, Achievement>(appExecutors) {
            override fun saveCallResult(item: Achievement) {
                achievementDao.insertAchievement(item)
            }
            override fun shouldFetch(data: Achievement?) = data == null

            override fun loadFromDb() = achievementDao.getAchievementById(achievementId)

            override fun createCall() = achievementService.getAchievement(achievementId)
        }.asLiveData()
    }

}