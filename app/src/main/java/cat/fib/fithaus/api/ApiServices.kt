package cat.fib.fithaus.api

import android.content.Context
import android.util.Log
import cat.fib.fithaus.models.User
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class ApiServices() {
    companion object {
        fun getUserInfo(
                id: Int,
                context: Context,
                listener: Response.Listener<User>,
                errorListener: Response.ErrorListener
        ) {

            val url = "https://192.168.1.131:8000/users/$id"
            val headers: MutableMap<String, String> = HashMap()
            val g: Request<User> = ApiRequests(url, User::class.java, headers, listener, errorListener)
            Log.println(Log.INFO, "API", g.toString())
            SingletonLoaders(context).addToRequestQueue(g)

        }
        fun login(
            email: String,
            pass: String,
            context: Context,
            listener: Response.Listener<String>,
            errorListener: Response.ErrorListener
        ) {
            val url = "https://192.168.1.131:8000/users/login?email=$email&password=$pass"

// Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                listener,
                errorListener)

            SingletonLoaders(context).addToRequestQueue(stringRequest)
        }
        fun postUserInfo(
            id: Int,
            context: Context,
            listener: Response.Listener<User>,
            errorListener: Response.ErrorListener
        ) {

            val url = "https://192.168.1.131:8000/users/$id"
            val headers: MutableMap<String, String> = HashMap()
            val g: Request<User> = ApiRequests(url, User::class.java, headers, listener, errorListener)
            Log.println(Log.INFO, "API", g.toString())
            SingletonLoaders(context).addToRequestQueue(g)

        }
    }

}
