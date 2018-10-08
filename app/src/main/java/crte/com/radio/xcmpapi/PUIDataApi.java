package crte.com.radio.xcmpapi;

import crte.com.radio.util.DataConvert;

public class PUIDataApi {
    private static final int ON_OFF = 0x0000;
    private static final int PPT = 0x0001;
    private static final int Rotary_Volume = 0x0002;
    private static final int Rotary_Channel = 0x0004;

    public static void receivePUIData(byte[] data) {
        int min = data[7];
        int max = data[8];
        int putId = DataConvert.byteToInt(data, 3, 2);
        switch (data[2]) {
            case 0x00:
                switch (putId) {
                    case PPT:
                        break;
                    case ON_OFF:
                        break;
                    case Rotary_Channel:
                        break;
                    case Rotary_Volume:
                        break;
                }
                break;
            case 0x03:
                break;
        }
    }
}
