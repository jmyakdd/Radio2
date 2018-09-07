//
// Created by CRTE-CD-13 on 2018/9/6.
//
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_jmy_mytestlibrary_JniTest_getString
        (JNIEnv *env, jobject object) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
