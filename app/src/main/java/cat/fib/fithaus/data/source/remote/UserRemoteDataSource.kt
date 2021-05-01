package cat.fib.fithaus.data.source.remote

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.api.UserService
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.UserDataSource
import cat.fib.fithaus.utils.Resource

class UserRemoteDataSource(
    userService : UserService
) : UserDataSource {

    override fun login(): LiveData<Resource<Int>> {
        userService.createUser(user)
    }

    override suspend fun getUser(userId: String): LiveData<Resource<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User) {
        userService.createUser(user)
    }





    //not necessary
    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
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

}
