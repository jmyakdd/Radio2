package crte.com.radio.xcmpapi;

import org.greenrobot.eventbus.EventBus;

import crte.com.radio.message.NormalMessageEvent;
import crte.com.radio.util.DataConvert;

public class ChannelUtil {
    public static void receiveChannelChangeData(byte[] data) {
        int channelNum = DataConvert.byteToInt(data, 4, 2);//获取信道号
        NormalMessageEvent normalMessageEvent = new NormalMessageEvent();
        normalMessageEvent.setCode(NormalMessageEvent.CHANGE_CHANNEL);
        normalMessageEvent.setData(channelNum);
        EventBus.getDefault().post(normalMessageEvent);
        if (data.length >= 7) {
            int status = data[6];
        }
    }

    public static void receiveChannelInfoData(byte[] data) {
        if (data[2] == 0x01) {
            return;
        }
        switch (data[3]) {
            case (byte) 0x80:
                int zoneNum = DataConvert.byteToInt(data, 4, 2);
                int channelNum = DataConvert.byteToInt(data, 6, 2);
                NormalMessageEvent normalMessageEvent = new NormalMessageEvent();
                normalMessageEvent.setCode(NormalMessageEvent.CHANGE_CHANNEL);
                normalMessageEvent.setData(channelNum);
                EventBus.getDefault().post(normalMessageEvent);
                break;
        }
    }
}
