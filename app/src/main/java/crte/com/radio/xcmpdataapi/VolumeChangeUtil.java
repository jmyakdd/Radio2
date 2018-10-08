package crte.com.radio.xcmpdataapi;

public class VolumeChangeUtil {
    public static void receiveVolumeChangeData(byte[] data) {
        //当前音量 range 0~255
        int volumeData = data[4];
    }
}
