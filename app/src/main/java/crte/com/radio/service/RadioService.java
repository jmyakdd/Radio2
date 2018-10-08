package crte.com.radio.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 后台电台状态信息获取
 * 负责处理电台状态信息相关广播消息
 */
public class RadioService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ReceiveThread receiveThread;

    @Override
    public void onCreate() {
        super.onCreate();
        receiveThread = new ReceiveThread();
        new Thread(receiveThread).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        receiveThread.setStart(false);
        super.onDestroy();
    }
}
