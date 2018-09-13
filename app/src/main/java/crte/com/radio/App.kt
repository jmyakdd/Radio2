package crte.com.radio

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import crte.com.greendao.DaoMaster
import crte.com.greendao.DaoSession

class App : Application() {

    private lateinit var mHelper: DaoMaster.DevOpenHelper
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
    }

    fun initDataBase() {
        mHelper = DaoMaster.DevOpenHelper(this, "test-db", null)
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