package cat.fib.fithaus.viewmodels

import androidx.lifecycle.*
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.source.ExerciseRepository
import cat.fib.fithaus.utils.Resource
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    /*
    private val _exerciseId = MutableLiveData<String>()

    private val _exercise = _exerciseId.switchMap { exerciseId ->
        exerciseRepository.observeExercise(exerciseId).map { computeResult(it) }
    }
    val exercises: LiveData<Exercise?> = _exercise


    init {
        // Set initial state

    }
    */
    /*
    private fun computeResult(taskResult: Resource<Exercise>): Exercise? {
        return if (taskResult is Resource.Success) {
            taskResult.data
        } else {
            null
        }
    }
    */

    fun getExercise(exerciseId: Int) {

    }

}