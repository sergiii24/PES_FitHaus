package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Collection
import cat.fib.fithaus.utils.Resource

interface CollectionRepository {

    fun getCollection(collectionName: String): LiveData<Resource<Collection>>
}