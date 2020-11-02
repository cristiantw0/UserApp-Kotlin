package com.example.userapp


import android.app.Application
import com.example.userapp.Entities.MyObjectBox
import com.example.userapp.Entities.User
import io.objectbox.Box
import io.objectbox.BoxStore


val preferencesHelper: PreferencesHelper by lazy { com.example.userapp.App.Companion.prefs!! }
val boxStore: BoxStore by lazy { com.example.userapp.App.Companion.boxStore!! }
val userBox: Box<User> by lazy { com.example.userapp.App.Companion.userBox!! }


class App : Application() {

    companion object {
        var prefs: PreferencesHelper? = null
        var shareInstance: App? = null
        var boxStore: BoxStore? = null
        var userBox: Box<User>? = null

    }

    override fun onCreate() {
        super.onCreate()

        com.example.userapp.App.Companion.prefs = PreferencesHelper(applicationContext)
        com.example.userapp.App.Companion.shareInstance = this

        com.example.userapp.App.Companion.boxStore = MyObjectBox.builder().androidContext(this).build()

        com.example.userapp.App.Companion.userBox = com.example.userapp.App.Companion.boxStore!!.boxFor(
            User::class.java)

        //Starting ObjectBox Data Browser (ONLY FOR TEST!!!)
        /*
        if (BuildConfig.DEBUG) {
            val started = AndroidObjectBrowser(boxStore).start(this)
            Log.i("ObjectBrowser", "Started: $started")
        }
        */

        //Instantiate Logging interceptor
        /*val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        //Build HTTP Client
        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        //Build Retrofit Client
        val retrofit = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        api = retrofit.create(Api::class.java)*/
    }

    @Synchronized
    fun getInstance(): App {
        return com.example.userapp.App.Companion.shareInstance!!
    }
}