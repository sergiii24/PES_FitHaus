package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.ShareAchievementService
import cat.fib.fithaus.data.models.ShareAchievement
import cat.fib.fithaus.data.source.local.ShareAchievementDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource

class ShareAchievementRepositoryDefault(
    private val shareAchievementDao: ShareAchievementDao,
    private val shareAchievementService: ShareAchievementService,
    private val appExecutors: AppExecutors
) : ShareAchievementRepository{

    override fun getShareAchievementById(shareAchievementId: Int): LiveData<Resource<List<ShareAchievement>>> {
        return object : NetworkDatabaseResource<List<ShareAchievement>, List<ShareAchievement>>(appExecutors) {
            override fun saveCallResult(items: List<ShareAchievement>) {
                for (i in items)
                {
                    shareAchievementDao.insertShareAchievement(i)
                }
            }
           // override fun shouldFetch(data: List<ShareAchievement>?) = data == null

            override fun loadFromDb() = shareAchievementDao. getShareAchievementById(shareAchievementId)

            override fun createCall() = shareAchievementService.getShareAchievement(shareAchievementId)
        }.asLiveData()
    }

}