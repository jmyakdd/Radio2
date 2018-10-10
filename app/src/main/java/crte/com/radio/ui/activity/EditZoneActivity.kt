package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.databinding.ActivityEidtZoneBinding
import crte.com.radio.message.EventBusAction
import crte.com.radio.util.ToastUtil
import crte.com.radio.viewModel.EditZoneViewModel
import org.greenrobot.eventbus.EventBus

class EditZoneActivity : BaseTitleActivity(), EditZoneViewModel.ViewCallBack {
    override fun saveSuccess() {
        ToastUtil.showShort("储存成功")
        EventBus.getDefault().post(EventBusAction.ACTION_REFRESH_ZONE)
    }

    override fun saveFail() {
        ToastUtil.showShort("储存失败")
    }

    lateinit var dataBinding: ActivityEidtZoneBinding
    lateinit var viewModel: EditZoneViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_eidt_zone)
        viewModel = EditZoneViewModel(this, this)
        dataBinding.viewModel = viewModel

        setBack()
        setTitle("区域编辑")

        var bundle = intent.getBundleExtra("bundle")
        viewModel.init(bundle)
    }
}