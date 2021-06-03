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

    override fun getClass(className: String): LiveData<Resource<Class>> {
        return object : NetworkBoundResource<Class, Class>(appExecutors) {
            override fun saveCallResult(item: Class) {
                classDao.insertClass(item)
            }

            override fun shouldFetch(data: Class?) = data == null

            override fun loadFromDb() = classDao.getClassByName(className)

            override fun createCall() = classService.getClass(className)
        }.asLiveData()
    }

    override fun getClasses(): LiveData<Resource<List<Class>>> {
        return object : NetworkDatabaseResource<List<Class>, List<Class>>(appExecutors) {
            override fun saveCallResult(items: List<Class>) {
                for (i in items) {
                    classDao.insertClass(i)
                }
            }

            override fun loadFromDb() = classDao.getClasses()

            override fun createCall() = classService.getClasses()
        }.asLiveData()
    }

}