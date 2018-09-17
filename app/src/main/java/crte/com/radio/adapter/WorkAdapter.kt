package crte.com.radio.adapter

import android.content.Context
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import crte.com.radio.R
import crte.com.radio.entry.Work

class WorkAdapter(val context: Context, val data: MutableList<Work>)
    : CommonAdapter<Work>(context, R.layout.item_work, data) {
    override fun convert(holder: ViewHolder?, t: Work?, position: Int) {
        if (holder != null) {
            if (t != null) {
                holder.setText(R.id.title, t.content)
                holder.setText(R.id.time, t.time.toString())
            }
        }
    }
}