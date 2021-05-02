package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Rutina
import retrofit2.http.GET
import retrofit2.http.Path


interface RutinaService {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the userId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/rutina/{id}")
    suspend fun getRutina(@Path("id") rutinaId: String): LiveData<ApiResponse<Rutina>>
}
