package com.evan.demo.ui.widget.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.widget.recyclerview.IViewHolder;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerViewAdapter的装饰类
 * Created by Evan on 2016/10/16.
 */
public final class HeaderAndFooterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE;

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<View> mHeaderViews; // HeaderView集合
    private List<View> mFooterViews; // FooterView集合

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + getHeaderViewCount(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + getHeaderViewCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + getHeaderViewCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            int headerViewsCountCount = getHeaderViewCount();
            notifyItemRangeChanged(fromPosition + headerViewsCountCount,
                    toPosition + headerViewsCountCount + itemCount);
        }
    };

    public HeaderAndFooterRecyclerViewAdapter(RecyclerView.Adapter innerAdapter) {
        setAdapter(innerAdapter);
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(@NonNull RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {

        if (mInnerAdapter != null) {
            notifyItemRangeRemoved(getHeaderViewCount(), mInnerAdapter.getItemCount());
            mInnerAdapter.unregisterAdapterDataObserver(mDataObserver);
        }

        this.mInnerAdapter = adapter;
        mInnerAdapter.registerAdapterDataObserver(mDataObserver);
        notifyItemRangeInserted(getHeaderViewCount(), mInnerAdapter.getItemCount());
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public void addHeaderView(View header) {

        if (header == null) {
            throw new RuntimeException("header is null");
        }

        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
        mHeaderViews.add(header);
        this.notifyDataSetChanged();
    }

    public void addFooterView(View footer) {

        if (footer == null) {
            throw new RuntimeException("footer is null");
        }

        if (mFooterViews == null) {
            mFooterViews = new ArrayList<>();
        }
        mFooterViews.add(footer);
        this.notifyDataSetChanged();
    }

    /**
     * 返回第一个HeaderView
     */
    public View getFirstHeaderView() {
        return getHeaderViewCount() > 0 ? mHeaderViews.get(0) : null;
    }

    /**
     * 返回第一个FooterView
     */
    public View getFirstFooterView() {
        return getFooterViewsCount() > 0 ? mFooterViews.get(0) : null;
    }

    public void removeHeaderView(View view) {
        if (mHeaderViews != null) {
            mHeaderViews.remove(view);
            // this.notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view) {
        if (mFooterViews != null) {
            mFooterViews.remove(view);
        }
        // this.notifyDataSetChanged();
    }

    public int getHeaderViewCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews == null ? 0 : mFooterViews.size();
    }

    public boolean isHeader(int position) {
        return getHeaderViewCount() > 0 && position == 0;
    }

    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - 1;
        return getFooterViewsCount() > 0 && position == lastPosition;
    }

    @Override
    public int getItemCount() {
        return getHeaderViewCount() + getFooterViewsCount() + mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int normalItemCount = mInnerAdapter.getItemCount();
        int headerViewCount = getHeaderViewCount();
        if (position < headerViewCount) {
            return TYPE_HEADER_VIEW + position;
        } else if (headerViewCount <= position && position < headerViewCount + normalItemCount) {
            int innerItemViewType = mInnerAdapter.getItemViewType(position - headerViewCount);
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException(
                        "your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;
        } else {
            return TYPE_FOOTER_VIEW + position - normalItemCount;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int headerViewCount = getHeaderViewCount();
        if (viewType < TYPE_HEADER_VIEW + headerViewCount) {
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
        } else if (viewType < Integer.MAX_VALUE / 2) {
            return new ViewHolder(mFooterViews.get(viewType - TYPE_FOOTER_VIEW - getHeaderViewCount()));
        } else {
            final RecyclerView.ViewHolder holder = mInnerAdapter.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
            if (holder instanceof IViewHolder) {
                View itemView = ((IViewHolder) holder).getItemView();
                if (itemView != null) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick(view, holder.getAdapterPosition() - getHeaderViewCount());
                            }
                        }
                    });
                    itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            if (mOnItemLongClickListener != null) {
                                return mOnItemLongClickListener.onItemLongClick(view, holder.getAdapterPosition() - getHeaderViewCount());
                            }
                            return false;
                        }
                    });
                }
            }
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerViewCount = getHeaderViewCount();
        if (position >= headerViewCount && position < headerViewCount + mInnerAdapter.getItemCount()) {
            mInnerAdapter.onBindViewHolder(holder, position - headerViewCount);
        } else {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
