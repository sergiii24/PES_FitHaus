package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Resource

interface UserRepository {

    suspend fun login(username: String, password: String): LiveData<Resource<Int>>

    fun getUser(userId: String): LiveData<Resource<User>>

    suspend fun createUser(user: User): LiveData<Resource<User>>

}