package crte.com.radio.xcmpapi;

public class GpsStatusUtil {
    public static void receiveGpsStatusData(byte[] data) {
        if (data[2] == 0x01) {//请求失败，电台没有gps模块
            return;
        }
        if (data[8] == 0x00) {//gps off

        } else if (data[8] == 0x01) {//gps on

        }
    }
}
