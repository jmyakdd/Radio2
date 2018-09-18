package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import crte.com.radio.R
import crte.com.radio.databinding.ActivityMessageDetailBinding
import crte.com.radio.viewModel.MessageDetailViewModel

class MessageDetailActivity : BaseTitleActivity(), MessageDetailViewModel.ViewCallBack, View.OnClickListener {
    override fun onClick(v: View?) {
        jump(ReplyMessageActivity::class.java)
    }

    lateinit var binding: ActivityMessageDetailBinding
    lateinit var viewModel: MessageDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message_detail)
        viewModel = MessageDetailViewModel(this, this)
        binding.viewModel = viewModel
        viewModel.setData()
        setBack()
        setTitle("短信详情")
        setRight("回复", this)
    }
}