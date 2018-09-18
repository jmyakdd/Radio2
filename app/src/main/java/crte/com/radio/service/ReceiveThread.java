package crte.com.radio.service;


import android.os.Handler;

import crte.com.radio.util.DataConvert;

public class ReceiveThread implements Runnable {
    private final static int RECEIVE_BROADCAST_PHYSICAL_USER_INPUT = 0xB405;//Physical User Input Broadcast


    private Handler handler;
    private boolean isStart = false;

    public ReceiveThread(Handler handler) {
        this.handler = handler;
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
            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void analyticalData(byte[] data) {
        int code = DataConvert.byteToInt(data, 0, 2);
        switch (code) {
            case RECEIVE_BROADCAST_PHYSICAL_USER_INPUT:

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
