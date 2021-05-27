package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import cat.fib.fithaus.data.api.*
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.source.local.UserDao
import cat.fib.fithaus.utils.AppExecutors
import cat.fib.fithaus.utils.Resource


/**
 * Default implementation of [UserRepository].
 */
class UserRepositoryDefault(
    private val userDao: UserDao,
    private val userService: UserService,
    private val appExecutors: AppExecutors,
) : UserRepository {
    override fun login(userEmail: String, userPassword: String): LiveData<Resource<User>> {
        return object : NetworkDatabaseResource<User, User>(appExecutors) {

            override fun createCall() = userService.login(userEmail, userPassword)

            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
            }

            override fun loadFromDb() = userDao.getUserByEmail(userEmail)

        }.asLiveData()
    }

    override fun getUserByEmail(email: String): LiveData<Resource<User>> {
        return object : NetworkDatabaseResource<User, User>(appExecutors) {

            override fun createCall() = userService.getUserByEmail(email)

            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
            }

            override fun loadFromDb() = userDao.getUserByEmail(email)

        }.asLiveData()
    }

    /*
    override fun createUser(user: User): LiveData<Resource<User>> {

        val result = MediatorLiveData<Resource<User>>()
        val value = userService.createUser(user)

        result.addSource(value) { response ->
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.mainThread().execute {

                        result.setValue(Resource.success(response.body))

                    }
                }

                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        result.setValue(Resource.loading(null))
                    }
                }


            }
        }

        return result as LiveData<Resource<User>>
    }
    */

    override fun createUser(user: User): LiveData<Resource<User>> {
        return object : NetworkDatabaseResource<User, User>(appExecutors) {

            override fun createCall() = userService.createUser(user)

            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
                user.id = item.id
            }

            override fun loadFromDb() = userDao.getUserById(user.id.toString())

        }.asLiveData()
    }

    override fun getUser(userId: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.getUserById(userId)

            override fun createCall() = userService.getUser(userId)
        }.asLiveData()
    }

    override fun updateUser(userId: Int, updatedUser: User): LiveData<Resource<User>> {
        return object : NetworkDatabaseResource<User, User>(appExecutors) {

            override fun createCall() = userService.updateUser(userId, updatedUser)

            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
                //updatedUser.id = item.id
            }

            override fun loadFromDb() = userDao.getUserById(updatedUser.id.toString())

        }.asLiveData()
    }
    override fun deleteUser(userId: Int): LiveData<Resource<User>> {
        return object : NetworkDatabaseResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.deleteUser(userId.toString())
            }

            override fun loadFromDb() = userDao.getUserById(userId.toString())

            override fun createCall() = userService.deleteUser(userId.toString())

        }.asLiveData()
    }


}