package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.HealthData
import retrofit2.Call
import retrofit2.http.*
import java.net.URLDecoder


interface HealthDataService {

    /**
     * @POST declares an HTTP POST request
     */
    @POST("/healthdata/")
    fun createHealthData(@Body healthData: HealthData): LiveData<ApiResponse<HealthData>>


    @GET("/healthdata")
    fun getHealthDataByUserId(@Query("user_id") user_id: Int
    ): LiveData<ApiResponse<HealthData>>

}

