package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Exercise
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

interface ExerciseService {
     /**
         * @GET declares an HTTP GET request
         * @Path("user") annotation on the userId parameter marks it as a
         * replacement for the {user} placeholder in the @GET path
         */
        @GET("/exercise/{user}")
        suspend fun getUser(@Path("user") userId: String): LiveData<ApiResponse<Exercise>>

}
