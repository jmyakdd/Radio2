package crte.com.radio.util;

/**
 * 协议封装类
 */
public class XcmpData1Util {

    public static final byte BASE_INFO_RSSI = 0x02;
    public static final byte BASE_INFO_MODEL_NUMBER = 0x07;
    public static final byte BASE_INFO_SERIAL_NUMBER = 0x08;
    public static final byte BASE_INFO_SIGNALING = 0x0d;
    public static final byte BASE_INFO_RADIO_ID = 0x0e;

    /**
     * _00e
     * 电台基础信息
     */
    public static byte[] baseInfo(byte condition) {
        byte[] data = new byte[3];
        data[0] = 0x00;
        data[1] = 0x0e;
        data[2] = condition;
        return data;
    }


    public static final byte VERSION_INFO_HOST_SOFTWARE = 0x00;
    public static final byte VERSION_INFO_DSP = 0x010;
    public static final byte VERSION_INFO_REGIONAL_INFORMATION = 0x47;
    public static final byte VERSION_INFO_RF_BAND = 0x63;
    public static final byte VERSION_INFO_POWER_LEVEL = 0x65;
    public static final byte VERSION_INFO_FLASH_SIZE = 0x6D;

    /**
     * _00f
     *
     * @param type
     * @return
     */
    public static byte[] sendGetVersionInfo(byte type) {
        byte[] data = new byte[3];
        data[0] = 0x00;
        data[1] = 0x0f;
        data[2] = type;
        return data;
    }


    public static final byte TONE_FUN_STOP = 0x00;
    public static final byte TONE_FUN_START = 0x01;
    public static final byte TONE_FUN_DISABLE = 0x02;
    public static final byte TONE_FUN_ENABLE = 0x03;

    public static final int TONE_IDENTIFIER_NONE = 0x0000;
    public static final int TONE_IDENTIFIER_START_TALKING = 0x0003;
    public static final int TONE_IDENTIFIER_GOOD_KEY = 0x0005;
    public static final int TONE_IDENTIFIER_BAD_KEY = 0x0006;
    public static final int TONE_IDENTIFIER_PRIORITY_BEEP = 0x000C;
    public static final int TONE_IDENTIFIER_POWER_UP_BEEP = 0x000D;

    /**
     * _409
     *
     * @return
     */
    public static byte[] sendTipVoice() {
        byte[] data = new byte[10];
        data[0] = 0x04;
        data[1] = 0x09;
        return data;
    }


    /**
     * _407
     *
     * @return
     */
    public static byte[] sendSpeakerControl() {
        byte[] data = new byte[10];
        data[0] = 0x04;
        data[1] = 0x09;
        return data;
    }

    public static final byte Power_Level_Set = 0x01;
    public static final byte Power_Level_Get = (byte) 0x80;
    public static final byte PWR_LVL_LOW = 0x00;
    public static final byte PWR_LVL_HIGH = 0x03;

    /**
     * _407
     * 设置/获取电台发射功率
     *
     * @return
     */
    public static byte[] sendPowerLevelSetting(byte methed, byte lvl) {
        byte[] data = new byte[4];
        data[0] = 0x04;
        data[1] = 0x08;
        data[2] = methed;
        data[3] = lvl;
        return data;
    }


}