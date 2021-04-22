package cat.fib.fithaus.exercise

import cat.fib.fithaus.data.api.ExerciseService

class ExerciseRemoteDataSource {

    val exerciseService : ExerciseService = ExerciseService()

    fun getExercise( id : Int) {
        return exerciseService.getExercice(id)
    }

}
