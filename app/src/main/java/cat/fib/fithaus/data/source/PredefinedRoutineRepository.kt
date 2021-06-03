package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.utils.Resource

interface PredefinedRoutineRepository {

    fun getPredefinedRoutine(predefinedRoutineId: Int): LiveData<Resource<PredefinedRoutine>>
}