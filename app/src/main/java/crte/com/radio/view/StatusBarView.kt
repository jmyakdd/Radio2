package crte.com.radio.view

interface StatusBarView {
    /**
     * rssi信号强度改变
     */
    fun rssiValueChange(value: Int)

    /**
     * 蓝牙状态改变
     */
    fun bluetoothStatusChange(isOpen: Boolean)

    /**
     * gps状态改变
     */
    fun gpsStatusChange(isOpen: Boolean)

    /**
     * 直通状态改变
     */
    fun modelStatusChange(state: Int)

    /**
     * 电池电量改变
     */
    fun batteryValueChange(value: Int)
}