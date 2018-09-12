package crte.com.radio

import android.app.Application

class App : Application() {

    companion object {
        var isConnect = false
        var isRecording = false
        var isCalling = false
        var isReceiving = false
        lateinit var instance:App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}