package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Program
import retrofit2.http.GET
import retrofit2.http.Path

interface ProgramService {
    @GET("/programs/{id}")
    fun getProgram(@Path("id") programId: Int): LiveData<ApiResponse<Program>>

}