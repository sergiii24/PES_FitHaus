package cat.fib.fithaus.api

import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.gson
import cat.fib.fithaus.models.serializeToMap
import cat.fib.fithaus.models.serializeToString
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.util.HashMap
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
            val request = ApiService(client.build())
            val url = "http://192.168.1.248:8000/users/$id"
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
            val request = ApiService(client.build())
            val url = "http://192.168.1.248:8000/users/login?email=$email&password=$pass"
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
            val request = ApiService(client.build())
            val userJson: String = gson.toJson(user)
            val url = "http://192.168.1.248:8000/users/"
            request.post(url, userJson, callback)
        }
    }

}
