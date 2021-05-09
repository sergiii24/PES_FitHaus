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


    override fun login(username: String, password: String): LiveData<Resource<Int>> {

        val result = MediatorLiveData<Resource<Int>>()
        val value = userService.login(username, password)

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
                        result.setValue(Resource.loading(0))
                    }
                }


            }
        }

        return result as LiveData<Resource<Int>>

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


}