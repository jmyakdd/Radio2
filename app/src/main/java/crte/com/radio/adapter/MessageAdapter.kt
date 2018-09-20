package crte.com.radio.adapter

import android.content.Context
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import crte.com.radio.R
import crte.com.radio.entry.Message
import crte.com.radio.util.DateTimeUtil

class MessageAdapter(val context: Context, val data: MutableList<Message>)
    : CommonAdapter<Message>(context, R.layout.item_work, data) {
    override fun convert(holder: ViewHolder?, t: Message?, position: Int) {
        if (holder != null) {
            if (t != null) {
                holder.setText(R.id.title, t.content)
                holder.setText(R.id.time, DateTimeUtil.getTimeOfDate(t.time))
            }
        }
    }
}