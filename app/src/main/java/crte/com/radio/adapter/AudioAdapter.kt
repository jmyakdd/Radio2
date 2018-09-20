package crte.com.radio.adapter

import android.content.Context
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import crte.com.radio.R
import crte.com.radio.entry.Voice

class AudioAdapter(val context: Context, val data: MutableList<Voice>)
    : CommonAdapter<Voice>(context, R.layout.item_work, data) {
    override fun convert(holder: ViewHolder?, t: Voice?, position: Int) {
        if (holder != null) {
            if (t != null) {
                holder.setText(R.id.title, t.fileName)
                holder.setText(R.id.time, t.fileName)
            }
        }
    }
}