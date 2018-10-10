package crte.com.radio.xcmpapi;

public class CallStatusUtil {
    private static final byte Type_No_Call_Feature = 0x00;
    private static final byte Type_Selective_Call = 0x01;
    private static final byte Type_Call_Alert = 0x02;
    private static final byte Type_Enhanced_Private_Call = 0x04;
    private static final byte Type_Private_Phone_Call = 0x05;
    private static final byte Type_Group_Call = 0x06;
    private static final byte Type_Call_Alert_With_Voice = 0x08;
    private static final byte Type_Telegram_Call = 0x09;
    private static final byte Type_Group_Phone_Call = 0x0A;

    public static void receiveCallStatus(byte[] data) {
        switch (data[2]) {
            case Type_No_Call_Feature:
                break;
            case Type_Selective_Call:
                break;
            case Type_Call_Alert:
                break;
            case Type_Enhanced_Private_Call:
                break;
            case Type_Private_Phone_Call:
                break;
            case Type_Group_Call://组呼
                getCallStatus(data);
                break;
            case Type_Call_Alert_With_Voice:
                break;
            case Type_Telegram_Call:
                break;
            case Type_Group_Phone_Call:
                break;
        }
    }

    private static final byte State_Call_Decoded = 0x01;
    private static final byte State_Call_In_Progress = 0x02;
    private static final byte State_Call_Ended = 0x03;
    private static final byte State_Call_Initiated = 0x04;
    private static final byte State_No_Ack = 0x06;
    private static final byte State_Call_In_Hangtime = 0x07;
    private static final byte State_Call_Decoded_Clear = 0x08;
    private static final byte State_Call_Decoded_Key_Matched = 0x09;
    private static final byte State_Call_Decoded_Key_Mismatched = 0x0A;
    private static final byte State_Call_Busy_Queued = 0x0B;
    private static final byte State_Channel_Assigned = 0x0C;
    private static final byte State_System_Denial = 0x0D;

    private static void getCallStatus(byte[] data) {
        switch (data[3]) {
            case State_Call_Ended://语音结束
                break;
            case State_Call_Initiated://语音请求
                break;
            case State_Call_In_Progress://语音发起中
                break;
            case State_Call_In_Hangtime://语音挂起（松开ptt）
                break;
        }
    }
}
