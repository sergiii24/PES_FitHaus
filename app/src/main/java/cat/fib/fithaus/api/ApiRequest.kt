package cat.fib.fithaus.api

/**
 * Created by Rohan Jahagirdar on 07-02-2018.
 */


import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*


class ApiRequest(client: OkHttpClient) {
    private var client = OkHttpClient()

    init {
        this.client = client
    }

    fun post(url: String, json: String, callback: Callback):

            Call {
        val builder = FormBody.Builder()
        val body: RequestBody = json
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()


        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    fun GET(url: String, callback: Callback):

            Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    companion object {
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    }
}