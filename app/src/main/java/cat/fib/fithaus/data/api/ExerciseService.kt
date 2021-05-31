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
         * @Path("id") annotation on the userId parameter marks it as a
         * replacement for the {id} placeholder in the @GET path
         */
        @GET("/exercises/{id}")
        fun getExercise(@Path("id") exerciseId: String): LiveData<ApiResponse<Exercise>>

        @GET("/exercises")
        fun getExercises() : LiveData<ApiResponse<List<Exercise>>>
}

