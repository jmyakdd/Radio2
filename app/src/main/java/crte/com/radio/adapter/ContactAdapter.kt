package crte.com.radio.adapter

import android.content.Context
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import crte.com.radio.R
import crte.com.radio.entry.Contact

class ContactAdapter(val context: Context, val data: MutableList<Contact>)
    : CommonAdapter<Contact>(context, R.layout.item_contact, data) {
    override fun convert(holder: ViewHolder?, t: Contact?, position: Int) {
        if (holder != null) {
            if (t != null) {
                holder.setText(R.id.contact_name, t.id.toString()+" "+t.name)
            }
        }
    }
}