package com.jmy.mycustomerviewlibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jmy.mycustomerviewlibrary.R;

public class MyRefreshListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_FOOTER = -1;
    private RecyclerView.Adapter adapter;
    private LayoutInflater inflater;
    private boolean isShowFooter;
    private boolean isNoData;

    public MyRefreshListAdapter(RecyclerView.Adapter adapter, Context context, boolean isShowFooter, boolean isNodata) {
        this.adapter = adapter;
        this.inflater = LayoutInflater.from(context);
        this.isShowFooter = isShowFooter;
        this.isNoData = isNodata;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        switch (i) {
            case TYPE_FOOTER:
                View footer = inflater.inflate(R.layout.footerview, viewGroup, false);
                viewHolder = new FooterHolder(footer);
                break;
            default:
                viewHolder = adapter.onCreateViewHolder(viewGroup, i);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof FooterHolder){
            FooterHolder footerHolder = (FooterHolder) viewHolder;
            footerHolder.progressBar.setVisibility(isNoData ?View.GONE:View.VISIBLE);
            footerHolder.textView.setText(isNoData ?"没有更多数据了":"加载中...");
        }else{
            adapter.onBindViewHolder(viewHolder,i);
        }
    }

    @Override
    public int getItemCount() {
        return isShowFooter ? adapter.getItemCount() + 1 : adapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowFooter && position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return adapter.getItemViewType(position);
        }
    }

    static class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.load_tip);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public void setShowFooter(boolean showFooter) {
        isShowFooter = showFooter;
        notifyDataSetChanged();
    }

    public void setNoData(boolean noData) {
        isNoData = noData;
        notifyDataSetChanged();
    }
}
