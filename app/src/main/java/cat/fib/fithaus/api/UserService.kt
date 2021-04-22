package cat.fib.fithaus.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException

class UserService {

    fun patchQuestionari(id : Int, nivell : String, categories : Array<String>, objectius : Array<String>) {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        val request = ApiRequest(client.build())
        val url = Configuration.urlServer+"/users/"+id

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("nivell", nivell)
        jsonObject.put("categories", categories)
        jsonObject.put("objectius", objectius)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        request.patch(url, jsonObjectString, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }

        })

    }
}