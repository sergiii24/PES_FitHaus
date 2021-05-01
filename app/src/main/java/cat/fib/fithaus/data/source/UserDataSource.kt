package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Resource

/**
   Necessary functions to implement in the sources

 */
interface UserDataSource {

    fun login(): LiveData<Resource<Int>>
    
    suspend fun getUser(userId: String): LiveData<Resource<User>>

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteAllUsers()

    suspend fun deleteUser(userId: String)

    suspend fun createUser(user: User)


}