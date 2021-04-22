package cat.fib.fithaus.factories

import cat.fib.fithaus.exercise.ExerciseRepository
import cat.fib.fithaus.exercise.ExerciseViewModel


// Factory for LoginViewModel.
// Since LoginViewModel depends on UserRepository, in order to create instances of
// LoginViewModel, you need an instance of UserRepository that you pass as a parameter.
class ExerciseViewModelFactory(private val exerciseRepository: ExerciseRepository) : Factory<ExerciseViewModel> {
    override fun create(): ExerciseViewModel {
        return ExerciseViewModel(exerciseRepository)
    }
}