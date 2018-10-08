package crte.com.radio.service;


import crte.com.radio.util.DataConvert;
import crte.com.radio.xcmpapi.CallStatusUtil;
import crte.com.radio.xcmpapi.ChannelChangeUtil;
import crte.com.radio.xcmpapi.GpsStatusUtil;
import crte.com.radio.xcmpapi.PUIDataApi;
import crte.com.radio.xcmpapi.RadioStatusUtil;
import crte.com.radio.xcmpapi.Test;
import crte.com.radio.xcmpapi.TransimitControlUtil;
import crte.com.radio.xcmpapi.VolumeChangeUtil;
import crte.com.radio.xcmpapi.WorkUtil;

public class ReceiveThread implements Runnable {

    private boolean isStart = false;

    public ReceiveThread() {
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
            Test.callStart();
//            radioService.callStart();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Test.callEnd();
//            radioService.callEnd();
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
    private final static int RECEIVE_REPLY_GPS_STATUS = 0x840B;//GPS status

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
                PUIDataApi.receivePUIData(data);
                break;
            case RECEIVE_BROADCAST_CALL_STATUS:
                CallStatusUtil.receiveCallStatus(data);
                break;
            case RECEIVE_BROADCAST_TRANSMIT_CONTROL:
                TransimitControlUtil.receiveTransmitControl(data);
                break;
            case RECEIVE_BROADCAST_CHANNEL_CHANGE:
                ChannelChangeUtil.receiveChannelChangeData(data);
                break;
            case RECEIVE_BROADCAST_WORK:
                WorkUtil.receiveWorkMsg(data);
                break;
            case RECEIVE_BROADCAST_TONE:

                break;
            case RECEIVE_BROADCAST_VOLUME:
                VolumeChangeUtil.receiveVolumeChangeData(data);
                break;
            case RECEIVE_REPLY_RADIO_STATUS:
                RadioStatusUtil.receiveRadioStatus(data);
                break;
            case RECEIVE_REPLY_GPS_STATUS:
                GpsStatusUtil.receiveGpsStatusData(data);
                break;
        }
    }

}
