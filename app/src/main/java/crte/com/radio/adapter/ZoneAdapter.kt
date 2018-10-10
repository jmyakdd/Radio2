package crte.com.radio.adapter

import android.content.Context
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import crte.com.radio.R
import crte.com.radio.entry.Zone

class ZoneAdapter(val context: Context, val data: MutableList<Zone>)
    : CommonAdapter<Zone>(context, R.layout.item_zone, data) {
    var position = 0
    fun setSelect(position: Int) {
        this.position = position
    }

    override fun convert(holder: ViewHolder?, t: Zone?, position: Int) {
        if (holder != null) {
            if (t != null) {
                holder.setText(R.id.name, t.name)
                if (this.position == position) {
                    holder.setText(R.id.select, "√")
                } else {
                    holder.setText(R.id.select, "")
                }
            }
        }
    }
}