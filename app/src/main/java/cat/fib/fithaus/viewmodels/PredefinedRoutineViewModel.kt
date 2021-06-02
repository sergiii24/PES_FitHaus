package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.PredefinedRoutine
import cat.fib.fithaus.data.source.PredefinedRoutineRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PredefinedRoutineViewModel @Inject constructor(
    private val predefinedRoutineRepository: PredefinedRoutineRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var predefinedRoutine : LiveData<Resource<PredefinedRoutine>>

    fun getPredefinedRoutine(id: Int){
        predefinedRoutine = predefinedRoutineRepository.getPredefinedRoutine(id)
    }
}