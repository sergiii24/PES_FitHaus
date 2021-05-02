package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Rutina
import cat.fib.fithaus.data.source.RutinaDataSource
import cat.fib.fithaus.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Concrete implementation of a data source as a db.
 */
class RutinaLocalDataSource internal constructor(
    private val rutinaDao: RutinaDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RutinaDataSource {
    override suspend fun getRutinaById(rutinaId: String): LiveData<Resource<Rutina>> {
        TODO("Not yet implemented")
    }


}