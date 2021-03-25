package cat.fib.fithaus.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.Volley
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.security.KeyStore
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection


class SingletonLoaders constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: SingletonLoaders? = null
        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: SingletonLoaders(context).also {
                        INSTANCE = it
                    }
                }
    }
    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.

        val trustStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        trustStore.load(null, null)

        val hostnameVerifier: HostnameVerifier = HostnameVerifier { hostname, session ->
            val hv: HostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
            hv.verify("localhost", session)
        }

        val hurlStack: HurlStack = object : HurlStack() {
            @Throws(IOException::class)
            override fun createConnection(url: URL?): HttpURLConnection? {
                val httpsURLConnection = super.createConnection(url) as HttpsURLConnection
                try {
                    httpsURLConnection.sslSocketFactory = ClientSSLSocketFactory.sslSocketFactory().factory
                    httpsURLConnection.hostnameVerifier = hostnameVerifier
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return httpsURLConnection
            }
        }

        Volley.newRequestQueue(
            context.applicationContext, hurlStack
        )

    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}