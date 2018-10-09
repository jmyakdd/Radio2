package crte.com.radio.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendDataUtil {
    private ExecutorService executor;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;

    public SendDataUtil() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void initConnnect(final Handler handler, final Context context) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void sendData(final byte[] data) {
        if (outputStream == null) {
            Log.e("SendDataUtil", "socket is not init");
            return;
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.write(data);
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void close() {
        if (outputStream != null) {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}