package crte.com.radio.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import crte.com.radio.R
import crte.com.radio.api.ApiServiceHelper
import crte.com.radio.api.BaseListResponseResult
import crte.com.radio.entry.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu.setOnClickListener {
            jump(ContactActivity::class.java)
        }
        contact.setOnClickListener {
            //            jump(ContactActivity::class.java)
            ApiServiceHelper.getInstance().apiService.getUserList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<BaseListResponseResult<User>> {

                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(t: BaseListResponseResult<User>) {
                            log(t.toString())
                        }

                        override fun onError(e: Throwable) {

                        }

                    })
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 101)
                    return
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 101) {
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
