package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Class
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassService {

    /**
     * @GET declares an HTTP GET request
     * @Path("name") annotation on the className parameter marks it as a
     * replacement for the {name} placeholder in the @GET path
     */
    @GET("/classes/{name}")
    fun getClass(@Path("name") className: String): LiveData<ApiResponse<Class>>

}