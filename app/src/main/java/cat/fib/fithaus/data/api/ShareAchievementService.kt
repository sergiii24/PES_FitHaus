package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.ShareAchievement
import retrofit2.http.GET
import retrofit2.http.Query

interface ShareAchievementService {

    @GET("/shareachievement")
    fun getShareAchievement(@Query("user") userId: Int): LiveData<ApiResponse<List<ShareAchievement>>>

}
