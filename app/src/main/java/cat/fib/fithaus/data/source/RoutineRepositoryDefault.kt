package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.RoutineService
import cat.fib.fithaus.data.models.CustomRoutine
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.data.source.local.CustomRoutineDao
import cat.fib.fithaus.data.source.local.PredefinedRoutineDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation of [RoutineRepository].
 */
class RoutineRepositoryDefault(
    private val customRoutineDao: CustomRoutineDao,
    private val predefinedRoutineDao: PredefinedRoutineDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val appExecutors: AppExecutors,
    ) : RoutineRepository {
    override fun getCustomRoutineById(rutinaId: String): LiveData<Resource<CustomRoutine>> {
        return object : NetworkBoundResource<CustomRoutine, CustomRoutine>(appExecutors) {
            override fun saveCallResult(item: CustomRoutine) {
                customRoutineDao.insertCustomRoutine(item)
            }

            override fun shouldFetch(data: CustomRoutine?) = data == null

            override fun loadFromDb() = customRoutineDao.getCustomRoutineById(rutinaId)

            override fun createCall() = RoutineService.getCustomRoutine(rutinaId)
        }.asLiveData()
    }

    override fun getPredefinedRoutineById(rutinaId: String): LiveData<Resource<PredefinedRoutine>> {
        return object : NetworkBoundResource<PredefinedRoutine, PredefinedRoutine>(appExecutors) {
            override fun saveCallResult(item: PredefinedRoutine) {
                predefinedRoutineDao.insertPredefinedRoutine(item)
            }

            override fun shouldFetch(data: PredefinedRoutine?) = data == null

            override fun loadFromDb() = predefinedRoutineDao.getPredefinedRoutineById(rutinaId)

            override fun createCall() = RoutineService.getPredefinedRoutine(rutinaId)
        }.asLiveData()
    }
}