package crte.com.radio.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import crte.com.radio.api.ApiService
import crte.com.radio.api.ApiServiceHelper
import crte.com.radio.entry.NetStateMessageEvent
import crte.com.radio.entry.StatusMessageCode
import crte.com.radio.entry.StatusMessageEvent
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
    fun onReceiveMessage(msgEvent: StatusMessageEvent) {
        when (msgEvent.code) {
            StatusMessageCode.RSSI->{

            }
            StatusMessageCode.POWER_STATUS->{

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

    /**
     * 设置发射状态
     */
    fun setTransmitMode(state: Int) {

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
     * 直至直通模式
     */
    fun setConnectMode(mode: Int) {

    }

    /**
     * 设置与电台建联状态
     */
    fun setConnectStatus(status: Boolean) {

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