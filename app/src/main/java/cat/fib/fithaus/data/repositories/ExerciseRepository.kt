package cat.fib.fithaus.data.repositories

import cat.fib.fithaus.exercise.ExerciseLocalDataSource
import cat.fib.fithaus.exercise.ExerciseRemoteDataSource

class ExerciseRepository(
    private val localDataSource: ExerciseLocalDataSource,
    private val remoteDataSource: ExerciseRemoteDataSource
){

    fun getExercise(id : Int) {
        //TODO mirar si està en local sino anirem a remot, ara només anem a remote
        return remoteDataSource.getExercise(id)
    }



}