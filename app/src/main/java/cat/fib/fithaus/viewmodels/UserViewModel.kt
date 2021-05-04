package cat.fib.fithaus.viewmodels

import androidx.lifecycle.*
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: String =
        savedStateHandle["id"] ?: throw IllegalArgumentException("missing user id")

    val user = userRepository.getUser(userId)


    fun create(user: User) {

        viewModelScope.launch {
            userRepository.createUser(user)
        }

    }

}
