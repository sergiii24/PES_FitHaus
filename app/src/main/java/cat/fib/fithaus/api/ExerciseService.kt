package cat.fib.fithaus.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class ExerciseService {


    fun getExercice(id: Int) {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        val request = ApiRequest(client.build())
        val url = Configuration.urlServer+"/exercises/"+id
        request.GET(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }

        })
    }


}