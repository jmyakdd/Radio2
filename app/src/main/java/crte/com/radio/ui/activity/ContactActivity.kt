package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.ContactAdapter
import crte.com.radio.databinding.ActivityContactBinding
import crte.com.radio.util.ToastUtil
import crte.com.radio.viewModel.ContactViewModel

class ContactActivity : BaseTitleActivity(), RefreshLoadListener, ContactViewModel.ViewCallBack {
    lateinit var binding: ActivityContactBinding
    lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(System.currentTimeMillis().toString())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact)
        setBack()
        setTitle("通讯录")
        viewModel = ContactViewModel(this, this)
        var adapter = ContactAdapter(this, viewModel.datas.get()!!)
//        binding.viewModel = viewModel
        binding.myRecyclerview.setAdapter(adapter)
        binding.myRecyclerview.setLayoutManager(LinearLayoutManager(this))
        binding.myRecyclerview.setLoadListener(this)

        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtil.showShort(viewModel.datas.get()!!.get(position).name)
            }
        })

        log(System.currentTimeMillis().toString())
    }

    override fun onResume() {
        super.onResume()
        binding.myRecyclerview.startRefresh()
        log(System.currentTimeMillis().toString())
    }

    override fun completeRefresh() {
        binding.myRecyclerview.notifyDataSetChanged()
        binding.myRecyclerview.stopRefresh()
    }

    override fun completeLoad() {
        binding.myRecyclerview.notifyDataSetChanged()
        binding.myRecyclerview.stopLoad()
    }

    override fun upLoad() {
        viewModel.startLoad()
    }

    override fun downRefresh() {
        viewModel.startRefresh()
    }
}
