package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Class
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassService {

    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the classId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/classes/{id}")
    fun getClass(@Path("id") classId: String): LiveData<ApiResponse<Class>>

}