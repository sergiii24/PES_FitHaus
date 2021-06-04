package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.CollectionService
import cat.fib.fithaus.data.source.local.CollectionDao
import cat.fib.fithaus.data.models.Collection
import cat.fib.fithaus.data.models.Program
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource


/**
 * Default implementation of [CollectionRepository].
 */
class CollectionRepositoryDefault (
    private val collectionDao: CollectionDao,
    private val collectionService: CollectionService,
    private val appExecutors: AppExecutors,
) : CollectionRepository {
    override fun getCollection(collectionName:String): LiveData<Resource<Collection>> {
        return object : NetworkBoundResource<Collection, Collection>(appExecutors) {
            override fun saveCallResult(item: Collection) {
                collectionDao.insertCollection(item)
            }

            override fun shouldFetch(data: Collection?) = data == null

            override fun loadFromDb() = collectionDao.getCollectionByName(collectionName)

            override fun createCall() = collectionService.getCollection(collectionName)
        }.asLiveData()
    }

    override fun getCollections(): LiveData<Resource<List<Collection>>> {
        return object : NetworkDatabaseResource<List<Collection>, List<Collection>>(appExecutors) {
            override fun saveCallResult(items: List<Collection>) {
                for (i in items) {
                    collectionDao.insertCollection(i)
                }
            }

            override fun loadFromDb() = collectionDao.getCollections()

            override fun createCall() = collectionService.getCollections()
        }.asLiveData()
    }
}