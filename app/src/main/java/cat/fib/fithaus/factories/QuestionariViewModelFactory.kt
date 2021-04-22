package cat.fib.fithaus.factories

import cat.fib.fithaus.questionari.UserRepository
import cat.fib.fithaus.questionari.QuestionariViewModel

// Factory for LoginViewModel.
// Since LoginViewModel depends on UserRepository, in order to create instances of
// LoginViewModel, you need an instance of UserRepository that you pass as a parameter.
class QuestionariViewModelFactory(private val userRepository: UserRepository) : Factory<QuestionariViewModel> {
    override fun create(): QuestionariViewModel {
        return QuestionariViewModel(userRepository)
    }
}
