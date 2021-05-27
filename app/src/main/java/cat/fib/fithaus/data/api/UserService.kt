package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import retrofit2.Call
import retrofit2.http.*
import java.net.URLDecoder


interface UserService {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the userId parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("/users/{id}")
    fun getUser(@Path("id") userId: String): LiveData<ApiResponse<User>>

    /**
     * @POST declares an HTTP POST request
     */
    @POST("/users")
    fun createUser(@Body user: User): LiveData<ApiResponse<User>>

    @GET("/users/login")
    fun login(@Query("email", encoded = true) email: String,
              @Query("password") password: String
    ): LiveData<ApiResponse<User>>

    @PUT("/users/{id}")
    fun updateUser(@Path("id") userId: Int, @Body updatedUser: User): LiveData<ApiResponse<User>>

    @DELETE ("/users/{id}")
    fun deleteUser(@Path ("id") userId: String): LiveData<ApiResponse<User>>

    @GET("/users")
    fun getUserByEmail(@Query("email", encoded = true) email: String
    ): LiveData<ApiResponse<User>>

}

