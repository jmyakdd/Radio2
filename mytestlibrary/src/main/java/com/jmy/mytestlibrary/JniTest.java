package com.jmy.mytestlibrary;

public class JniTest {
    static {
        System.loadLibrary("myJniTest");
    }

    public native String getString();
}
