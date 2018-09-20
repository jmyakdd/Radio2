package crte.com.radio.test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class MyTestObserver<T> implements Observer<T> {

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }
}
