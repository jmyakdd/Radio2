package crte.com.radio.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import crte.com.radio.entry.NetStateMessageEvent
import crte.com.radio.util.NetUtil
import org.greenrobot.eventbus.EventBus

/**
 * 网络状态广播监听
 */
class NetWorkStatusReceive: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if(intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                var networkState = NetUtil.getNetWorkState(context)
                var netStateMessageEvent = NetStateMessageEvent()
                netStateMessageEvent.code = networkState
                EventBus.getDefault().post(netStateMessageEvent)
            }
        }
    }
}