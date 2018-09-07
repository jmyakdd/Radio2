package com.jmy.mycustomerviewlibrary.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PullRefreashView extends RecyclerView {
    private View footer;
    private View header;
    private PullState pullState;

    private enum PullState{
        //定义刷新状态 下拉刷新、释放刷新、正在刷新
        PULL_TO_REFRESH, RELEASE_REFRESH, REFRESHING
    }

    public PullRefreashView(@NonNull Context context) {
        super(context);
        init();
    }

    public PullRefreashView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullRefreashView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        Log.e("test",screenState+"");
    }
}
