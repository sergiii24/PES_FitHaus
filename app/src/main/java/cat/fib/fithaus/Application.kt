package cat.fib.fithaus

import android.app.Activity
import android.app.Application
import com.android.example.github.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class GithubApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: AndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}