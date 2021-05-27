package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.CustomRoutine
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.utils.Resource

interface RoutineRepository {

   fun getCustomRoutineById(rutinaId: Int): LiveData<Resource<CustomRoutine>>

   fun getPredefinedRoutineById(rutinaId: Int): LiveData<Resource<PredefinedRoutine>>

}