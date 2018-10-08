package crte.com.radio.xcmpdataapi;

import crte.com.radio.util.DataConvert;

public class ChannelChangeUtil {
    public static void receiveChannelChangeData(byte[] data) {
        int channelNum = DataConvert.byteToInt(data, 4, 2);//获取信道号
        if (data.length >= 7) {
            int status = data[6];
        }
    }
}
