package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Achievement
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AchievementService {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the achievementId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/achievements")
    fun getAchievement(@Query("id") id: Int): LiveData<ApiResponse<Achievement>>

   /* @GET("/achievements/{id}")
    fun getAchievement(@Path("id") achievementId: Int): LiveData<ApiResponse<Achievement>> */
}