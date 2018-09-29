package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.databinding.ActivityFunctionBinding
import crte.com.radio.viewModel.FunctionViewModel

class FunctionActivity : BaseTitleActivity(), FunctionViewModel.ViewCallBack {

    lateinit var binding: ActivityFunctionBinding
    lateinit var viewModel: FunctionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_function)
        viewModel = FunctionViewModel(this, this)
        binding.viewModel = viewModel
        setBack()
        setTitle("功能")
        binding.about.setOnClickListener { jump(AboutActivity::class.java) }
        binding.record.setOnClickListener { jump(AudioActivity::class.java) }
        binding.video.setOnClickListener { jump(VideoActivity::class.java) }
    }
}