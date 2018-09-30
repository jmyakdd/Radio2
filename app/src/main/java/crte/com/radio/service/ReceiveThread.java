package crte.com.radio.service;


import crte.com.radio.util.DataConvert;
import crte.com.radio.util.XcmpDataUtil;

public class ReceiveThread implements Runnable {

    private boolean isStart = false;
    private IRadioService radioService;

    public ReceiveThread(IRadioService radioService) {
        this.radioService = radioService;
        isStart = true;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (isStart) {
            radioService.callStart();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            radioService.callEnd();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 广播code
     */
    private final static int RECEIVE_BROADCAST_PHYSICAL_USER_INPUT = 0xB405;//Physical User Input Broadcast
    private final static int RECEIVE_BROADCAST_CALL_STATUS = 0xB41E;//呼叫控制
    private final static int RECEIVE_BROADCAST_TRANSMIT_CONTROL = 0xB415;//传输状态
    private final static int RECEIVE_BROADCAST_CHANNEL_CHANGE = 0xB40D;//信道切换
    private final static int RECEIVE_BROADCAST_TONE = 0xB409;//播放Tone音
    private final static int RECEIVE_BROADCAST_VOLUME = 0xB406;//音量控制

    /**
     * 工单code
     */
    private final static int RECEIVE_BROADCAST_WORK = 0x0120;

    /**
     * 请求Reply
     */
    private final static int RECEIVE_REPLY_RADIO_STATUS = 0x800E;

    /**
     * 数据解析入口
     *
     * @param data 接收到电台发送来的数据
     */
    private void analyticalData(byte[] data) {
        //xcmp code
        int code = DataConvert.byteToInt(data, 0, 2);
        switch (code) {
            case RECEIVE_BROADCAST_PHYSICAL_USER_INPUT:
                receivePUIData(data);
                break;
            case RECEIVE_BROADCAST_CALL_STATUS:
                receiveCallStatus(data);
                break;
            case RECEIVE_BROADCAST_TRANSMIT_CONTROL:
                receiveTransmitControl(data);
                break;
            case RECEIVE_BROADCAST_CHANNEL_CHANGE:
                receiveChannelChangeData(data);
                break;
            case RECEIVE_BROADCAST_WORK:
                receiveWorkMsg(data);
                break;
            case RECEIVE_BROADCAST_TONE:

                break;
            case RECEIVE_BROADCAST_VOLUME:
                receiveVolumeChangeData(data);
                break;

            case RECEIVE_REPLY_RADIO_STATUS:
                receiveRadioStatus(data);
                break;

        }
    }

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

    private void receiveTransmitControl(byte[] data) {
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

    private void receiveRadioStatus(byte[] data) {
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

    private void receiveVolumeChangeData(byte[] data) {
        //当前音量 range 0~255
        int volumeData = data[4];
    }

    private void receiveWorkMsg(byte[] data) {
        int groupId = DataConvert.byteToInt(data, 5, 3);
        int length = DataConvert.byteToInt(data, 11, 2);
        byte[] content = new byte[length];
        System.arraycopy(data, 13, content, 0, length);

        int head = DataConvert.byteToInt(content, 0, 3);

    }

    private void receiveChannelChangeData(byte[] data) {
        int channelNum = DataConvert.byteToInt(data, 4, 2);//获取信道号
        if (data.length >= 7) {
            int status = data[6];
        }
    }

    private static final byte Type_No_Call_Feature = 0x00;
    private static final byte Type_Selective_Call = 0x01;
    private static final byte Type_Call_Alert = 0x02;
    private static final byte Type_Enhanced_Private_Call = 0x04;
    private static final byte Type_Private_Phone_Call = 0x05;
    private static final byte Type_Group_Call = 0x06;
    private static final byte Type_Call_Alert_With_Voice = 0x08;
    private static final byte Type_Telegram_Call = 0x09;
    private static final byte Type_Group_Phone_Call = 0x0A;

    private void receiveCallStatus(byte[] data) {
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

    private void getCallStatus(byte[] data) {
        switch (data[3]) {
            case State_Call_Ended://语音结束
                break;
            case State_Call_Initiated://语音开始
                break;
        }
    }

    private static final int ON_OFF = 0x0000;
    private static final int PPT = 0x0001;
    private static final int Rotary_Volume = 0x0002;
    private static final int Rotary_Channel = 0x0004;

    private void receivePUIData(byte[] data) {
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
