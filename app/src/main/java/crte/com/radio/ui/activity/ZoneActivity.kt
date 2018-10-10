package crte.com.radio.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.ZoneAdapter
import crte.com.radio.databinding.ActivityZoneBinding
import crte.com.radio.message.EventBusAction
import crte.com.radio.viewModel.ZoneViewModel
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ZoneActivity : BaseTitleActivity(), ZoneViewModel.ViewCallBack, MultiItemTypeAdapter.OnItemClickListener {
    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        return true
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        var menus = arrayOf("切换", "修改")
        dialog.setItems(menus, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (menus[which].equals("切换")) {
                    adapter.setSelect(position)
                    adapter.notifyDataSetChanged()
                } else {
                    var bundle = Bundle()
                    bundle.putString("type","change")
                    bundle.putString("name", viewModel.data.get()!!.get(position).name)
                    bundle.putString("num", viewModel.data.get()!!.get(position).id.toString())
                    jump(EditZoneActivity::class.java,bundle)
                }
                dialog!!.dismiss()
            }
        })
        dialog.show()
    }

    override fun loadDataComplete() {
        adapter.notifyDataSetChanged()
    }

    lateinit var dataBinding: ActivityZoneBinding
    lateinit var viewModel: ZoneViewModel
    lateinit var adapter: ZoneAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_zone)
        viewModel = ZoneViewModel(this, this)
        dataBinding.viewModel = viewModel

        setBack()
        setTitle("区域列表")
        setRight("添加", object : View.OnClickListener {
            override fun onClick(v: View?) {
                var bundle = Bundle()
                bundle.putString("type","add")
                jump(EditZoneActivity::class.java,bundle)
            }
        })

        adapter = ZoneAdapter(this, viewModel.data.get()!!)
        adapter.setOnItemClickListener(this)
        adapter.setSelect(0)

        dataBinding.rv.adapter = adapter
        dataBinding.rv.layoutManager = LinearLayoutManager(this)
        dataBinding.rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.initData()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveAction(action: String) {
        if (action.equals(EventBusAction.ACTION_REFRESH_ZONE)) {
            viewModel.initData()
        }
    }
}