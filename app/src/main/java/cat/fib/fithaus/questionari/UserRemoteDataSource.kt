package cat.fib.fithaus.questionari

import cat.fib.fithaus.api.UserService

class UserRemoteDataSource {
    val userService : UserService = UserService()

    fun patchQuestionari(id : Int, nivell : String, categories : Array<String>, objectius : Array<String>) {
        userService.patchQuestionari(id, nivell, categories, objectius)
    }

}