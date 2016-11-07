package com.evan.demo.ui.widget.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;

import java.util.List;

/**
 * RecyclerView的通用adapter（可添加header和footer）
 * Created by evanyu on 16/10/28.
 * @deprecated
 */
public abstract class DefaultRecyclerViewAdapter2<E> extends BaseRecyclerViewAdapter<E> {

    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterAdapter;

    public DefaultRecyclerViewAdapter2(Context context) {
        this(context, null, NO_ID);
    }

    public DefaultRecyclerViewAdapter2(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    public DefaultRecyclerViewAdapter2(Context context, List<E> data) {
        this(context, data, NO_ID);
    }

    public DefaultRecyclerViewAdapter2(Context context, List<E> data, int layoutId) {
        super(context, data, layoutId);
        init();
    }

    /**
     * init adapter
     */
    private void init() {
        mHeaderAndFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(this);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mHeaderAndFooterAdapter.setOnItemClickListener(listener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mHeaderAndFooterAdapter.setOnItemLongClickListener(listener);
    }

    public void addHeaderView(View header) {
        mHeaderAndFooterAdapter.addHeaderView(header);
    }

    public void addFooterView(View footer) {
        mHeaderAndFooterAdapter.addFooterView(footer);
    }

    /**
     * 返回第一个HeaderView
     */
    public View getFirstHeaderView() {
        return mHeaderAndFooterAdapter.getFirstHeaderView();
    }

    /**
     * 返回第一个FooterView
     */
    public View getFirstFooterView() {
        return mHeaderAndFooterAdapter.getFirstFooterView();
    }

    public void removeHeaderView(View view) {
        mHeaderAndFooterAdapter.removeHeaderView(view);
    }

    public void removeFooterView(View view) {
        mHeaderAndFooterAdapter.removeFooterView(view);
    }

    public int getHeaderViewCount() {
        return mHeaderAndFooterAdapter.getHeaderViewCount();
    }

    public int getFooterViewsCount() {
        return mHeaderAndFooterAdapter.getFooterViewsCount();
    }

    @Override
    public int getItemCount() {
        return getHeaderViewCount() + getFooterViewsCount() + super.getItemCount();
    }

    public int getNormalItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int normalItemCount = this.getItemCount();
        int headerViewCount = this.getHeaderViewCount();
        if (position < headerViewCount) {
            return HeaderAndFooterRecyclerViewAdapter.TYPE_HEADER_VIEW + position;
        } else if (headerViewCount <= position && position < headerViewCount + normalItemCount) {
            int itemViewType = this.getNormalItemViewType(position - headerViewCount);
            if (itemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException(
                        "your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return itemViewType + Integer.MAX_VALUE / 2;
        } else {
            return HeaderAndFooterRecyclerViewAdapter.TYPE_FOOTER_VIEW + position - normalItemCount;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mHeaderAndFooterAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mHeaderAndFooterAdapter.onBindViewHolder(holder, position);
    }
}
