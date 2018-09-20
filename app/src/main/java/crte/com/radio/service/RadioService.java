package crte.com.radio.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

public class RadioService extends Service {
    private Handler handler = new MyHandler(this);

    public static final String CALL_START = "com.crte.radio.CALL_START";
    public static final String CALL_END = "com.crte.radio.CALL_END";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case CALL_START:
                    showDialog();
                    break;
                case CALL_END:
                    cancelDialog();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ReceiveThread receiveThread;

    @Override
    public void onCreate() {
        super.onCreate();
        initDialog();
        receiveThread = new ReceiveThread(handler);
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

    private AlertDialog alertDialog;

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Receive new Message show or not");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("owen", "Yes is clicked");
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("owen", "No is clicked");
            }
        });

        alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
    }

    private void showDialog() {
        alertDialog.show();
    }

    private void cancelDialog() {
        alertDialog.cancel();
    }

    static class MyHandler extends Handler {
        WeakReference<Service> mWeakReference;

        public MyHandler(Service service) {
            mWeakReference = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            final RadioService service = (RadioService) mWeakReference.get();
            if (service != null) {
                switch (msg.what) {
                    case 0:
                        service.showDialog();
                        break;
                    case 1:
                        service.cancelDialog();
                        break;
                }
            }
            super.handleMessage(msg);
        }
    }
}
