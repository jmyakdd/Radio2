package crte.com.radio.ui.activity

import android.app.Activity
import android.app.AlarmManager
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact)
        setBack()
        setTitle("通讯录")
        setRight("编辑", object : View.OnClickListener {
            override fun onClick(v: View?) {
//                jump(EditContactActivity::class.java)
                startActivityForResult(Intent(this@ContactActivity, EditContactActivity::class.java), 101)
            }
        })
        var alarmManager:AlarmManager
        viewModel = ContactViewModel(this, this)
        var adapter = ContactAdapter(this, viewModel.datas.get()!!)
//        binding.viewModel = viewModel
        binding.myRecyclerview.setAdapter(adapter)
        binding.myRecyclerview.setLayoutManager(LinearLayoutManager(this))
        binding.myRecyclerview.setLoadListener(this)
        var divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.myRecyclerview.setDivider(divider)

        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtil.showShort(viewModel.datas.get()!!.get(position).name)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                binding.myRecyclerview.startRefresh()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.myRecyclerview.startRefresh()
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
