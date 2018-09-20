package crte.com.radio

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import crte.com.greendao.DaoMaster
import crte.com.greendao.DaoSession
import crte.com.greendao.MyOpenHelper
import crte.com.radio.util.MyLogUtil

class App : Application() {

    private lateinit var mHelper: MyOpenHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var mDaoMaster: DaoMaster
    private lateinit var mDaoSession: DaoSession

    companion object {
        var isConnect = false
        var isRecording = false
        var isCalling = false
        var isReceiving = false
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDataBase()
        initStorage()
    }

    private fun initStorage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            MyLogUtil.isCanStorage = true
        } else {
            MyLogUtil.isCanStorage = false
            return
        }
        MyLogUtil.getInternalMemorySize(this)
        MyLogUtil.getAvailableInternalMemorySize(this)
    }

    fun initDataBase() {
        mHelper = MyOpenHelper(this, "radio-db", null)
        db = mHelper.writableDatabase
        mDaoMaster = DaoMaster(db)
        mDaoSession = mDaoMaster.newSession()
    }

    fun getDaoSession(): DaoSession {
        return mDaoSession
    }

    fun getDb(): SQLiteDatabase {
        return db
    }
}