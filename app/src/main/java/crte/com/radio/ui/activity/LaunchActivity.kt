package crte.com.radio.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import crte.com.radio.R
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)


        var animation: Animation? = AnimationUtils.loadAnimation(this, R.anim.welcome_anim)
        animation!!.interpolator = LinearOutSlowInInterpolator()
        bg.startAnimation(animation)
        animation!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
    }
}