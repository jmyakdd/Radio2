package crte.com.radio.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.title_bar.*

abstract class BaseTitleActivity : BaseActivity() {

    fun setBack() {
        back.visibility = View.VISIBLE
        back.setOnClickListener {
            finish()
        }
    }

    fun setTitle(titleStr: String) {
        title_txt.text = titleStr
    }

    fun setRight(rightStr: String, listener: View.OnClickListener) {
        right_txt.text = rightStr
        right_txt.setOnClickListener(listener)
    }
}