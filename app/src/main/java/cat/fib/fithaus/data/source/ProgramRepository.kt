package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.Program
import cat.fib.fithaus.utils.Resource

interface ProgramRepository {
    fun getProgram(programId: Int): LiveData<Resource<Program>>
}