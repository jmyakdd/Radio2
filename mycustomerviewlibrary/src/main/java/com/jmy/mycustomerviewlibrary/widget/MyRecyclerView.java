package com.jmy.mycustomerviewlibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmy.mycustomerviewlibrary.R;
import com.jmy.mycustomerviewlibrary.adapter.MyRefreshListAdapter;
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener;

public class MyRecyclerView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView refreshList;
    private TextView no_data_tip;
    private RefreshLoadListener loadListener;
    private MyRefreshListAdapter meRefreshListAdapter;
    private Context mContext;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isShowFooter;
    private boolean isNoData;
    private int lastVisibleItem;

    public MyRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.my_recyclerview, this, true);
        refreshLayout = findViewById(R.id.swipe);
        no_data_tip = findViewById(R.id.no_data_tip);
        refreshList = findViewById(R.id.rv);
        refreshLayout.setOnRefreshListener(this);
        refreshList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int totalCount = layoutManager.getItemCount() - 1;
                if (totalCount > 18 && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == totalCount && !isNoData && !isShowFooter) {
                    if (null != loadListener) {
                        setFooter();
                        loadListener.upLoad();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
            }
        });
    }

    private void setFooter() {
        isShowFooter = true;
        meRefreshListAdapter.setShowFooter(true);
    }

    public void stopRefresh(int pageCount, boolean isNoData) {
        this.isNoData = isNoData;
        meRefreshListAdapter.setNoData(isNoData);
        showData(meRefreshListAdapter.getItemCount() > 0);
        if (pageCount == 1) {
//            refreshLayout.setRefreshing(false);
        } else {
            if (!isNoData) {
                isShowFooter = false;
                meRefreshListAdapter.setShowFooter(isShowFooter);
            }
        }
        refreshLayout.setRefreshing(false);
    }

    private void showData(boolean b) {
        refreshList.setVisibility(b ? VISIBLE : VISIBLE);
        no_data_tip.setVisibility(b ? GONE : VISIBLE);
    }

    @Override
    public void onRefresh() {
        if (null != loadListener) {
            isNoData = false;
            isShowFooter = false;
            meRefreshListAdapter.setNoData(isNoData);
            meRefreshListAdapter.setShowFooter(isShowFooter);
            loadListener.downRefresh();
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        refreshList.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        meRefreshListAdapter = new MyRefreshListAdapter(adapter, mContext, isShowFooter, isNoData);
        refreshList.setAdapter(meRefreshListAdapter);
    }

    public void notifyDataSetChanged() {
        meRefreshListAdapter.notifyDataSetChanged();
    }

    public void startRefresh() {
        refreshLayout.setRefreshing(true);
        onRefresh();
    }

    public void setLoadListener(RefreshLoadListener loadListener) {
        this.loadListener = loadListener;
    }
}
