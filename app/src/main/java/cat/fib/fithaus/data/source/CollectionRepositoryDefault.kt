package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.CollectionService
import cat.fib.fithaus.data.source.local.CollectionDao
import cat.fib.fithaus.data.models.Collection
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
        return object : NetworkDatabaseResource<Collection, Collection>(appExecutors) {
            override fun saveCallResult(item: Collection) {
                collectionDao.insertCollection(item)
            }

            override fun loadFromDb() = collectionDao.getCollectionByName(collectionName)

            override fun createCall() = collectionService.getCollection(collectionName)
        }.asLiveData()
    }

}