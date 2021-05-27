package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.CustomRoutine
import cat.fib.fithaus.data.models.PredefinedRoutine
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.*
import java.net.URLDecoder

interface RoutineService {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the routineId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/customroutines/{id}")
    fun getCustomRoutineById(@Path("id") rutinaId: String): LiveData<ApiResponse<CustomRoutine>>

    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the routineId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/predefinedroutines/{id}")
    fun getPredefinedRoutineById(@Path("id") rutinaId: String): LiveData<ApiResponse<PredefinedRoutine>>

   }