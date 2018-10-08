package crte.com.radio.xcmpapi;

public class TransimitControlUtil {
    private static final int MODE_VOICE = 0x00;
    private static final int Standby_Receive = 0x00;
    private static final int Transmit = 0x01;
    private static final int Transmit_TOT_Warning = 0x02;

    private static final int TX_NO_REASON = 0x00;
    private static final int LOW_BATTERY_TO_TX = 0x01;
    private static final int OUT_OF_RANGE = 0x02;
    private static final int CHANNEL_BUSY = 0x03;//信道忙
    private static final int NACK_RECEIVED = 0x04;
    private static final int ACK_FAILED = 0x05;
    private static final int TIMEOUT_TIMER_EXPIRED = 0x06;
    private static final int TX_FAILED = 0x07;

    public static void receiveTransmitControl(byte[] data) {
        if (data[2] != MODE_VOICE) {
            return;
        }
        switch (data[3]) {
            case Standby_Receive://可以接收语音
                switch (data[4]){
                    case OUT_OF_RANGE:
                        break;
                    case CHANNEL_BUSY:
                        break;
                }
                break;
            case Transmit://可以开始传输语音
                break;
            case Transmit_TOT_Warning:
                break;
        }
    }
}
