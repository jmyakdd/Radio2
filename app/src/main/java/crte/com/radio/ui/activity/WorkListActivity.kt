package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.WorkAdapter
import crte.com.radio.databinding.ActivityWorkListBinding
import crte.com.radio.viewModel.WorkListViewModel

class WorkListActivity : BaseTitleActivity(), WorkListViewModel.ViewCallBack, MultiItemTypeAdapter.OnItemClickListener, RefreshLoadListener {
    override fun upLoad() {

    }

    override fun downRefresh() {
        viewModel.refreshData()
    }

    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        return true
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun completeRefresh() {
        binding.myRv.notifyDataSetChanged()
        binding.myRv.stopRefresh()
    }

    override fun completeLoad() {

    }

    lateinit var binding: ActivityWorkListBinding
    lateinit var viewModel: WorkListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_list)
        viewModel = WorkListViewModel(this, this)
        binding.viewModel = viewModel
        setTitle("工单列表")
        setBack()
        setRight("添加",object :View.OnClickListener{
            override fun onClick(v: View?) {
                viewModel.insert()
            }
        })
        var adapter = WorkAdapter(this, viewModel.data.get()!!)
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