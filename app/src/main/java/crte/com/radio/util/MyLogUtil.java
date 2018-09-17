package crte.com.radio.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

public class MyLogUtil {
    public static boolean isCanStorage = true;//isExternalStorageAvailable
    private static String max;
    private static String currentMax;

    /**
     * 获取手机内部存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        long size = blockCountLong * blockSizeLong;
        max = Formatter.formatFileSize(context, size);
        return max;
    }

    /**
     * 获取手机内部可用存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getAvailableInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        long blockSizeLong = statFs.getBlockSizeLong();
        currentMax = Formatter.formatFileSize(context, availableBlocksLong
                * blockSizeLong);
        return currentMax;
    }
}
