package crte.com.radio.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import crte.com.radio.api.ApiService
import crte.com.radio.api.ApiServiceHelper
import crte.com.radio.entry.Contact
import crte.com.radio.entry.StatusMessageEvent
import crte.com.radio.util.ToastUtil
import kotlinx.android.synthetic.main.status_bar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onStart() {
        super.onStart()
        setRssiLevel(3)
        setBatteryLevel(50)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMessage(msgEvent: StatusMessageEvent) {
        when (msgEvent.code) {
            -1 -> {
                var contact: Contact = msgEvent.obj as Contact
                log(msgEvent.message!! + contact.name)
                ToastUtil.showShort(msgEvent.message!! + contact.name)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun setRssiLevel(level: Int) {
        iv_rssi.drawable.setLevel(10000 * level / 4)
    }

    fun setBatteryLevel(level: Int) {
        iv_battery.drawable.setLevel(10000 * level / 100)
    }

    fun jump(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    fun log(msg: String) {
        Log.e("test", msg)
    }

    fun getApiService(): ApiService {
        return ApiServiceHelper.getInstance().apiService
    }
}