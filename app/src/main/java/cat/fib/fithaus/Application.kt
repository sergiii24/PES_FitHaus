package cat.fib.fithaus

import android.app.Application

class Application : Application() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()

}