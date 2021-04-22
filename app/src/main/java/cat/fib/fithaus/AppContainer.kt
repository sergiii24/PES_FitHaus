package cat.fib.fithaus

import cat.fib.fithaus.questionari.UserLocalDataSource
import cat.fib.fithaus.questionari.UserRemoteDataSource
import cat.fib.fithaus.questionari.UserRepository
import cat.fib.fithaus.factories.QuestionariViewModelFactory

class AppContainer {

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before

    private val remoteDataSource = UserRemoteDataSource()
    private val localDataSource = UserLocalDataSource()

    // userRepository is not private; it'll be exposed
    val userRepository = UserRepository(localDataSource, remoteDataSource)
    val questionariViewModelFactory = QuestionariViewModelFactory(userRepository)


}