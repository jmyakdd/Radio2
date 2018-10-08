package crte.com.radio.xcmpdataapi;

import org.greenrobot.eventbus.EventBus;

import crte.com.radio.entry.NormalMessageEvent;

public class Test {
    public static void callStart(){
        NormalMessageEvent normalMessageEvent = new NormalMessageEvent();
        normalMessageEvent.setCode(NormalMessageEvent.CALL_START);
        normalMessageEvent.setMessage("通话开始");
        EventBus.getDefault().post(normalMessageEvent);
    }

    public static void callEnd(){
        NormalMessageEvent normalMessageEvent = new NormalMessageEvent();
        normalMessageEvent.setCode(NormalMessageEvent.CALL_END);
        normalMessageEvent.setMessage("通话结束");
        EventBus.getDefault().post(normalMessageEvent);
    }
}
