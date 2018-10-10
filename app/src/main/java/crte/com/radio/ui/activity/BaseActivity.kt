package crte.com.radio.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import crte.com.radio.api.ApiService
import crte.com.radio.api.ApiServiceHelper
import crte.com.radio.message.NetStateMessageEvent
import crte.com.radio.message.StatusMessageCode
import crte.com.radio.message.StatusMessageEvent
import crte.com.radio.util.NetUtil
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
        setRssiLevel(100)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveStatusMessage(msgEvent: StatusMessageEvent) {
        when (msgEvent.code) {
            StatusMessageCode.RSSI -> {
                setRssiLevel(msgEvent.obj as Int)
            }
            StatusMessageCode.POWER_STATUS -> {

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveNetMessage(msgEvent: NetStateMessageEvent) {
        when (msgEvent.code) {
            NetUtil.NETWORK_NONE -> showNoneNet()
            NetUtil.NETWORK_MOBILE -> showMobileNet()
            NetUtil.NETWORK_WIFI -> showWifiNet()
        }
    }

    open fun showMobileNet() {
        ToastUtil.showShort("当前使用的是移动4G网络")
    }

    open fun showWifiNet() {
        ToastUtil.showShort("当前使用的是无线wifi网络")
    }

    open fun showNoneNet() {
        ToastUtil.showShort("当前无网络连接")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 设置rssi值 0~255
     * level 0-4
     * rssi<85 4
     * 85<rssi<100 3
     * 100<rssi<105 2
     * 105<rssi<110 1
     * rssi>110 0
     */
    fun setRssiLevel(rssi: Int) {
        var level: Int
        if (rssi < 85) {
            level = 4
        } else if (rssi >= 85 && rssi < 100) {
            level = 3
        } else if (rssi >= 100 && rssi < 105) {
            level = 2
        } else if (rssi >= 105 && rssi < 110) {
            level = 1
        } else if (rssi >= 110) {
            level = 0
        } else {
            level = 0
        }
        iv_rssi.drawable.setLevel(10000 * level / 4)
    }

    /**
     * 设置电池电量 0~100
     */
    fun setBatteryLevel(level: Int) {
        iv_battery.drawable.setLevel(10000 * level / 100)
    }

    fun setPowerLevel(level: Int){

    }

    /**
     * 设置发射状态
     */
    fun setTransmitMode(state: Int) {
        if (state == 0) {//可接收状态

        } else if (state == 1) {//正在发射状态

        }
    }

    /**
     * 设置wifi状态
     */
    fun setWifiStatus(state: Boolean) {

    }

    /**
     * 设置Gps状态
     */
    fun setGpsStatus(state: Boolean) {

    }

    /**
     * 直通/转接模式
     */
    fun setConnectMode(mode: Int) {
        if (mode == 0) {//直通状态

        } else {//转接状态

        }
    }

    /**
     * 设置与电台建联状态
     */
    fun setConnectStatus(status: Boolean) {
        if (status) {//建立连接

        } else {//连接断开

        }
    }

    fun jump(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    fun jump(clazz: Class<*>, bundle: Bundle) {
        startActivity(Intent(this, clazz).putExtra("bundle", bundle))
    }

    fun log(msg: String) {
        Log.e("test", msg)
    }

    fun getApiService(): ApiService {
        return ApiServiceHelper.getInstance().apiService
    }
}