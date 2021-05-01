package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import retrofit2.Call
import retrofit2.http.*


interface UserService {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the userId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") userId: String): LiveData<ApiResponse<User>>

    /**
     * @POST declares an HTTP POST request
     */
    @POST("/users")
    suspend fun createUser(@Body user: User): Call<User>

    @GET("/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): LiveData<ApiResponse<Int>>

}

