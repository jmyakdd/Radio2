package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.databinding.ActivityAboutBinding
import crte.com.radio.viewModel.AboutViewModel

class AboutActivity : BaseTitleActivity(), AboutViewModel.ViewCallBack {
    lateinit var binding: ActivityAboutBinding
    lateinit var viewModel: AboutViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
        viewModel = AboutViewModel(this, this)
        binding.viewModel = viewModel
        setBack()
        setTitle("关于")
    }
}