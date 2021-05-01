package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.UserDataSource
import cat.fib.fithaus.utils.Resource
import cat.fib.fithaus.utils.Resource.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class UserLocalDataSource internal constructor(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    override suspend fun getUser(userId: String): LiveData<Resource<User>> {
        return userDao.getUserById(userId)
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun login(): LiveData<Resource<Int>> {
        TODO("Not yet implemented")
    }
}
