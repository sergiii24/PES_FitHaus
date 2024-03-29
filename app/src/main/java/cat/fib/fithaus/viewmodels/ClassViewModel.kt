package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.Class
import cat.fib.fithaus.data.source.ClassRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
        private val classRepository: ClassRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var classe : LiveData<Resource<Class>>?= null
    var classes: LiveData<Resource<List<Class>>>? = null

    fun getClass(name: String) {
        classe = classRepository.getClass(name)
    }

    fun getClasses(){
        classes = classRepository.getClasses()
    }


}