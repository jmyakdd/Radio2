package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.MessageAdapter
import crte.com.radio.databinding.ActivityMessageBinding
import crte.com.radio.viewModel.MessageViewModel

class MessageActivity : BaseTitleActivity(), MessageViewModel.ViewCallBack, MultiItemTypeAdapter.OnItemClickListener, RefreshLoadListener {
    override fun completeRefresh() {
        binding.myRv.notifyDataSetChanged()
        binding.myRv.stopRefresh()
    }

    override fun completeLoad() {

    }

    override fun upLoad() {

    }

    override fun downRefresh() {
        viewModel.refreshData()
    }

    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        return true
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        jump(MessageDetailActivity::class.java)
    }

    lateinit var binding: ActivityMessageBinding
    lateinit var viewModel: MessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message)
        viewModel = MessageViewModel(this, this)
        binding.viewModel = viewModel
        setTitle("短信列表")
        setBack()
        setRight("写信", object : View.OnClickListener {
            override fun onClick(v: View?) {
                viewModel.insert()
            }
        })
        var adapter = MessageAdapter(this, viewModel.data.get()!!)
        adapter.setOnItemClickListener(this)
        binding.myRv.setAdapter(adapter)
        binding.myRv.setLayoutManager(LinearLayoutManager(this))
        binding.myRv.setLoadListener(this)
    }

    override fun onResume() {
        super.onResume()
        binding.myRv.startRefresh()
    }
}