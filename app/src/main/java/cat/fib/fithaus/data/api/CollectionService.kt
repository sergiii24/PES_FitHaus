package cat.fib.fithaus.data.api

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Collection
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectionService {

    @GET("/collections/{name}")
    fun getCollection(@Path("name") collectionName: String): LiveData<ApiResponse<Collection>>
}