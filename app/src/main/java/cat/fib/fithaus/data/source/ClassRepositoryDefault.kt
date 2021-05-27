package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.ClassService
import cat.fib.fithaus.data.models.Class
import cat.fib.fithaus.data.source.local.ClassDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource

class ClassRepositoryDefault(
        private val classDao: ClassDao,
        private val classService: ClassService,
        private val appExecutors: AppExecutors,
) : ClassRepository {

    override fun getClass(classId: String): LiveData<Resource<Class>> {
        return object : NetworkBoundResource<Class, Class>(appExecutors) {
            override fun saveCallResult(item: Class) {
                classDao.insertClass(item)
            }

            override fun shouldFetch(data: Class?) = data == null

            override fun loadFromDb() = classDao.getClassById(classId)

            override fun createCall() = classService.getClass(classId)
        }.asLiveData()
    }

}