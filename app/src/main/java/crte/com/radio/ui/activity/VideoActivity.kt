package crte.com.radio.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import crte.com.radio.R
import kotlinx.android.synthetic.main.activity_video.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class VideoActivity : BaseTitleActivity() {
    val RECORD_SYSTEM_VIDEO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        btn.setOnClickListener {
            var fileUri: Uri? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = getOutputMediaFile()?.let { it1 -> FileProvider.getUriForFile(this, "crte.com.radio.fileprovider", it1) }
            } else {
                fileUri = Uri.fromFile(getOutputMediaFile())//设置视频录制保存地址的uri
            }
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)     //限制持续时长
            startActivityForResult(intent, RECORD_SYSTEM_VIDEO)
        }
    }

    private fun getOutputMediaFile(): File? {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return null
        }
        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp")
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs()
        }
        // Create a media file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return File(mediaStorageDir.path + File.separator + "VID_" + timeStamp + ".mp4")
    }


}