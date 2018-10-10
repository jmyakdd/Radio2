package crte.com.radio.viewModel

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import crte.com.radio.dao.ZoneModel
import crte.com.radio.entry.Zone
import crte.com.radio.util.ToastUtil

class EditZoneViewModel (val context: Context, val callBack:ViewCallBack){
    val name = ObservableField<String>()
    val num = ObservableField<String>()
    val isEdit = ObservableBoolean()
    private var type: String? = null

    fun init(bundle: Bundle) {
        type = bundle.getString("type")
        if (type == "add") {
            this.isEdit.set(true)
        } else {
            name.set(bundle.getString("name"))
            num.set(bundle.getString("num"))
            this.isEdit.set(false)
        }
    }

    fun save() {
        if (name.get() == null || name.get() == "") {
            ToastUtil.showShort("区域名称不能为空")
            return
        }
        if (num.get() == null || num.get() == "") {
            ToastUtil.showShort("区域id不能为空")
            return
        }
        val zone = Zone()
        zone.id = java.lang.Long.valueOf(num.get()!!)
        zone.name = name.get()
        val result = ZoneModel.insert(zone)
        if (result == 0L) {
            callBack.saveFail()
        } else {
            callBack.saveSuccess()
            if (type == "add") {
                clear()
            }
        }
    }

    fun clear() {
        name.set("")
        num.set("")
    }

    interface ViewCallBack {
        fun saveSuccess()

        fun saveFail()
    }
}