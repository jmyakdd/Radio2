package crte.com.radio.test;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MyTestObservableOnSubscribe implements ObservableOnSubscribe {

    @Override
    public void subscribe(ObservableEmitter emitter) {
        try {
            Thread.sleep(3000);
            emitter.onNext("ssss");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
