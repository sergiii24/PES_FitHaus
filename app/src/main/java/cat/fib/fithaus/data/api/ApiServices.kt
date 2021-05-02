package cat.fib.fithaus.data.api

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class ApiServices() {
    companion object {
        fun getRutinaInfo(
            id: Int,
            callback: Callback
        ) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            val request = ApiRequest(client.build())
            val url = Configuration.urlServer +"/rutina/"+id
            request.GET(url, callback)
        }
    }
}
