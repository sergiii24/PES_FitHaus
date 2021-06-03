package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.Program
import cat.fib.fithaus.data.source.ProgramRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor (
    private val programRepository: ProgramRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var program : LiveData<Resource<Program>>?= null
    var programs: LiveData<Resource<List<Program>>>? = null

    fun getProgram(id: Int) {
        program = programRepository.getProgram(id)
    }

    fun getPrograms() {
        programs = programRepository.getPrograms()
    }
}