package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.models.ShareAchievement
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

interface ShareAchievementService {

    @GET("/shareachievement/")
    fun getShareAchievement(@Query("user") userId: Int): LiveData<ApiResponse<ShareAchievement>>

}
