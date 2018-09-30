package crte.com.radio.service;

import android.os.Environment;

import java.util.concurrent.ExecutorService;

public class FileStorageManager {
    private final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final String dirPath = filePath + "/crte/radio/audio";
    private ExecutorService executorService;
}
