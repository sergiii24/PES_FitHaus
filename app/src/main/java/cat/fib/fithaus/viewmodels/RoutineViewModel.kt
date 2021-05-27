package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.CustomRoutine
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.RoutineRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getCustomRoutineById(rutinaId: Int){
        routine = routineRepository.getCustomRoutineById(id)
    }
    fun getPredefinedRoutineById(rutinaId: Int) {
        routine = routineRepository.getCustomRoutineById(id)
    }
}
