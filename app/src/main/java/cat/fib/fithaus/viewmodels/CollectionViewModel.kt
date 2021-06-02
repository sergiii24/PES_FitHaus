package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.Collection
import cat.fib.fithaus.data.source.CollectionRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    lateinit var collection: LiveData<Resource<Collection>>

    fun getCollection(collectionName: String) {
        collection = collectionRepository.getCollection(collectionName)
    }
}