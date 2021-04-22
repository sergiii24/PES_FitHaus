package cat.fib.fithaus.questionari

import cat.fib.fithaus.api.UserService

class UserRepository (
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
    ){

        fun patchQuestionari(id : Int, nivell : String, categories : Array<String>, objectius : Array<String>) {
            //TODO mirar si està en local sino anirem a remot, ara només anem a remote
            remoteDataSource.patchQuestionari(id, nivell, categories, objectius)
        }


}