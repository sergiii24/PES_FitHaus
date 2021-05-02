package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Rutina
import cat.fib.fithaus.utils.Resource

interface RutinaRepository {

   suspend fun getRutinaById(rutinaId: String): LiveData<Resource<Rutina>>

}