package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Resource

interface UserRepository {

    fun login(userEmail: String, userPassword: String): LiveData<Resource<User>>

    fun getUser(userId: Int): LiveData<Resource<User>>

    fun createUser(user: User): LiveData<Resource<User>>

    fun deleteUser(userId: Int): LiveData<Resource<User>>

    fun updateUser(user: User): LiveData<Resource<User>>

    fun getUserByEmail(email: String): LiveData<Resource<User>>

}