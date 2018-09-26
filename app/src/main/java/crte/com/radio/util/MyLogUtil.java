package crte.com.radio.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class MyLogUtil {
    public static boolean isCanStorage = true;//isExternalStorageAvailable
    private static String max;
    private static String currentMax;
    private static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/crte/radio";

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

    public static void writeCustomerOption(String content) {
        File dir = new File(filePath + "/customer");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long systemTime = System.currentTimeMillis();
        String fileName = DateTimeUtil.formatData("yyyy-MM-dd", systemTime) + ".txt";
        File file = new File(dir, fileName);
        OutputStream outputStream = null;
        content = DateTimeUtil.formatData("yyyy-MM-dd hh:mm:ss", systemTime) + ":" + content + "\r\n";
        try {
            outputStream = new FileOutputStream(file,true);
            outputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            writeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            writeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeException(Exception ex) {
        File dir = new File(filePath + "/exception");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long systemTime = System.currentTimeMillis();
        String fileName = DateTimeUtil.formatData("yyyy-MM-dd hh:mm", systemTime) + ".txt";
        File file = new File(dir, fileName);
        OutputStream outputStream = null;
        PrintStream printStream = null;
        try {
            outputStream = new FileOutputStream(file,true);
            printStream = new PrintStream(outputStream);
            ex.printStackTrace(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                printStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
