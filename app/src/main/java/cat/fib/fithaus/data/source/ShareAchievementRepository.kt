package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.ShareAchievement
import cat.fib.fithaus.utils.Resource

interface ShareAchievementRepository {

    fun getShareAchievementById(shareAchievementId: Int): LiveData<Resource<List<ShareAchievement>>>

}