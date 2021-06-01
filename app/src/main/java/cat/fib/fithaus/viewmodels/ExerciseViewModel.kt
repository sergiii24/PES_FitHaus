package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.source.ExerciseRepository
import cat.fib.fithaus.term.Data
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

    lateinit var exercise: LiveData<Resource<Exercise>> // = exerciseRepository.getExercise("1000")
    var exercises: LiveData<Resource<List<Exercise>>> = exerciseRepository.getExercises()
    var ex = Data.getfun()
    var ex_filtered : MutableLiveData<MutableList<Exercise>> = MutableLiveData(ex)


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


    fun multipleFilter(
        difficultyFilter: MutableList<String>,
        listAllExercises: List<Exercise>
    ): MutableLiveData<MutableList<Exercise>> {
        val listExerciseAfterFiltering: MutableLiveData<MutableList<Exercise>> = MutableLiveData()
        for (exercise in listAllExercises) {
            if (difficultyFilter.firstOrNull { it == exercise.difficulty } != null || difficultyFilter.isEmpty()) {
                listExerciseAfterFiltering.value?.add(exercise)
            }
        }
        return listExerciseAfterFiltering
    }

    fun setFilter(diffculty: MutableList<String>) {
        ex_filtered = multipleFilter(diffculty, ex)
    }

}