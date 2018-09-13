package crte.com.radio.ui.activity

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.databinding.ActivityEditContactBinding
import crte.com.radio.util.ToastUtil
import crte.com.radio.viewModel.EditContactViewModel

class EditContactActivity : BaseTitleActivity(), EditContactViewModel.ViewCallBack {
    lateinit var binding: ActivityEditContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_contact)
        setBack()
        setTitle("编辑")
        var viewModel = EditContactViewModel(this, this)
        binding.viewModel = viewModel
    }

    override fun saveSuccess() {
        ToastUtil.showShort("储存成功")
        binding.viewModel!!.clear()
        setResult(Activity.RESULT_OK)
    }

    override fun saveFail() {
        ToastUtil.showShort("储存失败")
    }
}