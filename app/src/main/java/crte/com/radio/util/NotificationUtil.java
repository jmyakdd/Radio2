package crte.com.radio.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import crte.com.radio.R;

public class NotificationUtil {

    public static void createNotificat(Context context, String msg) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("标题")
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(0, notification);
    }
}
