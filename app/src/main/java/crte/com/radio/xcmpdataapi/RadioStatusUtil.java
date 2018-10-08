package crte.com.radio.xcmpdataapi;

import crte.com.radio.util.XcmpDataUtil;

public class RadioStatusUtil {
    public static void receiveRadioStatus(byte[] data) {
        if (data[2] == 0x01) {
            return;
        }
        switch (data[3]) {
            case XcmpDataUtil.BASE_INFO_RSSI:
                break;
            case XcmpDataUtil.BASE_INFO_MODEL_NUMBER:
                break;
            case XcmpDataUtil.BASE_INFO_SERIAL_NUMBER:
                break;
            case XcmpDataUtil.BASE_INFO_SIGNALING:
                break;
            case XcmpDataUtil.BASE_INFO_RADIO_ID:
                break;
        }
    }
}
