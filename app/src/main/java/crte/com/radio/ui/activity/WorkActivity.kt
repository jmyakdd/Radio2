package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.databinding.ActivityWorkBinding
import crte.com.radio.viewModel.WorkViewModel

class WorkActivity : BaseTitleActivity() {
    lateinit var viewModel: WorkViewModel
    lateinit var binding: ActivityWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work)
        viewModel = WorkViewModel(this)
        binding.viewModel = viewModel
        setTitle("工单")
        setBack()
        binding.newWork.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "new")
            jump(WorkListActivity::class.java, bundle)
        }
        binding.oldWork.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "old")
            jump(WorkListActivity::class.java, bundle)
        }
    }
}