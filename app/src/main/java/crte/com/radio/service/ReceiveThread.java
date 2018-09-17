package crte.com.radio.service;


import android.os.Handler;

public class ReceiveThread implements Runnable {
    private Handler handler;
    private boolean isStart = false;
    public ReceiveThread(Handler handler){
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
        while (isStart){
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
}
