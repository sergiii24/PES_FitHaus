package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.PredefinedRoutineService
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.data.source.local.PredefinedRoutineDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource

class PredefinedRoutineRepositoryDefault(
    private val predefinedRoutineDao: PredefinedRoutineDao,
    private val predefinedRoutineService: PredefinedRoutineService,
    private val appExecutors: AppExecutors
) : PredefinedRoutineRepository{

    override fun getPredefinedRoutine(predefinedRoutineId: Int): LiveData<Resource<PredefinedRoutine>> {
        return object : NetworkBoundResource<PredefinedRoutine, PredefinedRoutine>(appExecutors) {
            override fun saveCallResult(item: PredefinedRoutine) {
                predefinedRoutineDao.insertPredefinedRoutine(item)
            }

            override fun shouldFetch(data: PredefinedRoutine?) = data == null

            override fun loadFromDb() = predefinedRoutineDao.getPredefinedRoutineById(predefinedRoutineId)

            override fun createCall() = predefinedRoutineService.getPredefinedRoutine(predefinedRoutineId)
        }.asLiveData()
    }

}