package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Class
import cat.fib.fithaus.utils.Resource

interface ClassRepository {
    fun getClass(className: String): LiveData<Resource<Class>>

    fun getClasses(): LiveData<Resource<List<Class>>>
}