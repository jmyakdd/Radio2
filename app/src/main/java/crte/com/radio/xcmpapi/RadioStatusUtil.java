package crte.com.radio.xcmpapi;

import org.greenrobot.eventbus.EventBus;

import crte.com.radio.message.StatusMessageCode;
import crte.com.radio.message.StatusMessageEvent;
import crte.com.radio.util.DataConvert;
import crte.com.radio.util.XcmpDataUtil;

public class RadioStatusUtil {
    public static void receiveRadioStatus(byte[] data) {
        if (data[2] == 0x01) {
            return;
        }
        switch (data[3]) {
            case XcmpDataUtil.BASE_INFO_RSSI:
                int value = DataConvert.byteToInt(data, 4, 2);
                StatusMessageEvent messageEvent = new StatusMessageEvent(StatusMessageCode.RSSI, "");
                messageEvent.setObj(value);
                EventBus.getDefault().post(messageEvent);
                break;
            case XcmpDataUtil.BASE_INFO_MODEL_NUMBER:
                break;
            case XcmpDataUtil.BASE_INFO_SERIAL_NUMBER:
                break;
            case XcmpDataUtil.BASE_INFO_SIGNALING:
                break;
            case XcmpDataUtil.BASE_INFO_RADIO_ID:
                int radioId = DataConvert.byteToInt(data,4,4);
                break;
            case XcmpDataUtil.BASE_INFO_BLUETOOTH:
                if (data[5] == 0x00) {//bluetooth Disable

                } else if (data[5] == 0x01) {//bluetooth Enable

                }
                break;
        }
    }
}
