package crte.com.radio.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import crte.com.radio.R
import crte.com.radio.databinding.ActivityMainBinding
import crte.com.radio.entry.NormalMessageEvent
import crte.com.radio.service.RadioService
import crte.com.radio.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity(), MainViewModel.ViewCallBack {

    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA)

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = MainViewModel(this, this)
        binding.viewModel = mainViewModel
        menu.setOnClickListener {
            jump(FunctionActivity::class.java)
        }
        contact.setOnClickListener {
            jump(ContactActivity::class.java)
        }
        message.setOnClickListener {
            jump(MessageActivity::class.java)
        }
        work.setOnClickListener {
            jump(WorkListActivity::class.java)
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(applicationContext)) {
                //启动Activity让用户授权
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.setData(Uri.parse("package:$packageName"))
                startActivityForResult(intent, 100)
            } else {
                //执行6.0以上绘制代码
                startService(Intent(this, RadioService::class.java))
            }
            for (permission in permissions) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 101)
                    return
                }
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, RadioService::class.java))
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            startService(Intent(this, RadioService::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveNormalMessage(msg: NormalMessageEvent) {
        mainViewModel.dealMessage(msg)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 101) {
            startService(Intent(this, RadioService::class.java))
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
