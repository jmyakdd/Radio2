package crte.com.radio.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.ContactAdapter
import crte.com.radio.entry.Contact
import crte.com.radio.entry.StatusMessageEvent
import kotlinx.android.synthetic.main.activity_contact.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ContactActivity : BaseTitleActivity() {

    var data = mutableListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setBack()
        setTitle("通讯录")
        var adapter = ContactAdapter(this, data)
        for (i in 0..20) {
            var d = Contact()
            d.id = i
            d.name = "name${i}"
            data.add(d)
        }
        rv_contact.adapter = adapter
        rv_contact.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                Toast.makeText(this@ContactActivity, data.get(position).name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMessage(msgEvent: StatusMessageEvent) {
        when (msgEvent.code) {
            -1 -> {
                var contact: Contact = msgEvent.obj as Contact
                log(msgEvent.message!! + contact.name)
                showToast(msgEvent.message!! + contact.name)
            }
        }
    }
}
