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

    private boolean isStart = false;

    @Override
    public void onCreate() {
        super.onCreate();
        receiveThread = new ReceiveThread();
        new Thread(receiveThread).start();

        isStart = true;
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStart) {
                    App.Companion.getSendDataUtil().sendData(XcmpDataUtil.baseInfo(XcmpDataUtil.BASE_INFO_RSSI));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
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
        isStart = false;
        super.onDestroy();
    }
}
