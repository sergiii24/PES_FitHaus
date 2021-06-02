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

    lateinit var program : LiveData<Resource<Program>>

    fun getProgram(id: Int) {
        program = programRepository.getProgram(id)
    }
}