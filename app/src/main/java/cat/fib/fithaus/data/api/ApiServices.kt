package cat.fib.fithaus.data.api

import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.models.gson
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor;


class ApiServices() {
    companion object {
        fun getUserInfo(
            id: Int,
            callback: Callback
        ) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            val request = ApiRequest(client.build())
            val url = Configuration.urlServer+"/users/"+id
            request.GET(url, callback)
        }

        fun login(
            email: String,
            pass: String,
            callback: Callback
        ) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            val request = ApiRequest(client.build())
            val url = Configuration.urlServer+"/users/login?email=$email&password=$pass"
            request.GET(url, callback)
        }

        fun postUserInfo(
            user: User,
            callback: Callback
        ) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            val request = ApiRequest(client.build())
            val userJson: String = gson.toJson(user)
            val url = Configuration.urlServer+"/users/"
            request.post(url, userJson, callback)
        }
    }

}
