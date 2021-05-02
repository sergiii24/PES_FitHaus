package cat.fib.fithaus.data.source.remote

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.RutinaService
import cat.fib.fithaus.data.models.Rutina
import cat.fib.fithaus.data.source.RutinaDataSource
import cat.fib.fithaus.utils.Resource

class RutinaRemoteDataSource(
    rutinaService : RutinaService
) : RutinaDataSource {
    override suspend fun getRutinaById(rutinaId: String): LiveData<Resource<Rutina>> {
        TODO("Not yet implemented")
    }
}
