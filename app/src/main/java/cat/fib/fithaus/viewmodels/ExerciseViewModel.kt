package cat.fib.fithaus.viewmodels

import androidx.lifecycle.*
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.source.ExerciseRepository
import cat.fib.fithaus.utils.Resource
import cat.fib.fithaus.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }

    lateinit var exercise : LiveData<Resource<Exercise>> // = exerciseRepository.getExercise("1000")
    var exercises:  LiveData<Resource<List<Exercise>>> = exerciseRepository.getExercises()

    private val filtered: MutableStateFlow<Int> = MutableStateFlow(
        savedStateHandle.get(GROW_ZONE_SAVED_STATE_KEY) ?: NO_GROW_ZONE
    )

    fun getExercise(id: String) {
        println("LLEGA")
        exercise = exerciseRepository.getExercise(id)
        println("SALE")
    }




    private fun computeResult(taskResult: Resource<List<Exercise>>): List<Exercise>? {
        return if (taskResult.status == Status.SUCCESS) {
            taskResult.data
        } else {
            null
        }
    }


}