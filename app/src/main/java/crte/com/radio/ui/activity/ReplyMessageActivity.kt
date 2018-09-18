package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import crte.com.radio.R
import crte.com.radio.databinding.ActivityReplyMessageBinding
import crte.com.radio.viewModel.ReplyMessageViewModel

class ReplyMessageActivity : BaseTitleActivity(), ReplyMessageViewModel.ViewCallBack {
    lateinit var bingding: ActivityReplyMessageBinding
    lateinit var viewModel: ReplyMessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_reply_message)
        viewModel = ReplyMessageViewModel(this, this)
        bingding.viewModel = viewModel

        setBack()
        setTitle("写信")
        setRight("发送", object : View.OnClickListener {
            override fun onClick(v: View?) {
                viewModel.send()
            }
        })
    }
}