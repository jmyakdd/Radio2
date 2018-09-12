package crte.com.radio

import android.app.Application

class App : Application() {
    companion object {
        var isConnect = false
        var isRecording = false
        var isCalling = false
        var isReceiving = false
    }

    override fun onCreate() {
        super.onCreate()
    }
}