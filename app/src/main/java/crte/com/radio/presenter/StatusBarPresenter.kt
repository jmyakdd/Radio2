package crte.com.radio.presenter

import android.util.Log
import crte.com.radio.entry.StatusMessageEvent
import crte.com.radio.view.StatusBarView

class StatusBarPresenter (val statusBarView: StatusBarView){

    fun onReceiveMessage(messageEvent: StatusMessageEvent){
        Log.e("test",messageEvent.toString())
    }
}